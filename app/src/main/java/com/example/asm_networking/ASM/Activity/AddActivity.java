package com.example.asm_networking.ASM.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.asm_networking.ASM.Adapter.CategoryAdapter;
import com.example.asm_networking.ASM.Constant.Requests.NewProductRequest;
import com.example.asm_networking.ASM.Constant.Requests.UploadRequest;
import com.example.asm_networking.ASM.Constant.Respone.NewProductResponse;
import com.example.asm_networking.ASM.Constant.Respone.UploadResponse;
import com.example.asm_networking.ASM.Constant.Retrofits.RetrofitBuilder;
import com.example.asm_networking.ASM.Constant.Retrofits.iRetrofitService;
import com.example.asm_networking.ASM.Models.Category;
import com.example.asm_networking.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    List<Category> list;
    CategoryAdapter adapter;
    Button btnBack, btn_SaveAdd;
    EditText edtProductName, edtProductPrice, edtProductQuantity;
    TextView tvTakePicture;
    ImageView imgProduct;
    Spinner spCategories;

    private iRetrofitService service;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        list = new ArrayList<>();
        spCategories = findViewById(R.id.spCategories);

        btnBack = findViewById(R.id.btnBack);
        btn_SaveAdd = findViewById(R.id.btn_SaveAdd);
        edtProductName = findViewById(R.id.edtProductName);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductQuantity = findViewById(R.id.edtProductQuantity);
        tvTakePicture = findViewById(R.id.tvTakePicture);
        imgProduct = findViewById(R.id.imgProduct);
        runtimePermission();

        service = RetrofitBuilder.createService(iRetrofitService.class);
        loadCategory();
        if (!checkCamera()) {
            requestCamera();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


//        spCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                spCategories.setSelection(0);
//            }
//        });
    }

    public void onAddClick(View view) {
        String name = edtProductName.getText().toString();
        Integer quantity = Integer.parseInt(edtProductQuantity.getText().toString());
        Float price = Float.parseFloat(edtProductPrice.getText().toString());
        Integer index = spCategories.getSelectedItemPosition();
        Integer category_id = list.get(index).getId();
        NewProductRequest request = new NewProductRequest(name, price, quantity, path, category_id);
        service.insertProduct(request).enqueue(insertProduct);
    }

    public void loadCategory() {
        service.getCategories().enqueue(getCategories);
    }

    public Boolean checkCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_DENIED) {
            return false;
        }
        return true;
    }

    public void requestCamera() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, 2000);
    }

    public void onTakePictureClick() {
        Intent pick=new Intent(Intent.ACTION_GET_CONTENT);
        pick.setType("image/*");
        // l???y t??? camera
        Intent pho=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // T??ch h???p
        Intent chosser=Intent.createChooser(pick, "chon");

        chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{pho});

        try {
            activityResultLauncher.launch(chosser);
        } catch (Exception e) {
            Log.d(">>>>>>>>>", "onTakePictureClick" + e.getMessage());
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imgProduct.setImageBitmap(bitmap);
                    // upload h??nh d???ng base64 l??n server
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
                    byte[] bytes = baos.toByteArray();

                    String base64 = Base64.encodeToString(bytes, 0);
                    UploadRequest request = new UploadRequest(base64);
                    service.upload(request).enqueue(uploadImage);
                }
            });

    Callback<UploadResponse> uploadImage = new Callback<UploadResponse>() {
        @Override
        public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
            if (response.isSuccessful()) {
                UploadResponse result = response.body();
                Log.d(">>>>>>uploadResponse", result.getPath());
                path = result.getPath();
            }
        }

        @Override
        public void onFailure(Call<UploadResponse> call, Throwable t) {
            Log.d(">>> uploadImage", "onFailure: " + t.getMessage());
        }
    };

    Callback<List<Category>> getCategories = new Callback<List<Category>>() {
        @Override
        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            if (response.isSuccessful()) {
                List<Category> result = response.body();
                list.addAll(result);
                adapter = new CategoryAdapter(AddActivity.this, list);
                spCategories.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
            Log.d(">>> getCategories", "onFailure: " + t.getMessage());
        }
    };

    Callback<NewProductResponse> insertProduct = new Callback<NewProductResponse>() {
        @Override
        public void onResponse(Call<NewProductResponse> call, Response<NewProductResponse> response) {
            if (response.isSuccessful()) {
                NewProductResponse result = response.body();
                Toast.makeText(AddActivity.this, "Th??m s???n ph???m th??nh c??ng!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void onFailure(Call<NewProductResponse> call, Throwable t) {
            Toast.makeText(AddActivity.this, "Th??m s???n ph???m th???t b???i!", Toast.LENGTH_SHORT).show();
            Log.d(">>>", "insert: " + t.getMessage());
        }
    };

    private void runtimePermission() {
        Dexter.withContext(AddActivity.this).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        tvTakePicture.setOnClickListener(v->onTakePictureClick());
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }


}