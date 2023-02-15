package com.example.asm_networking.ASM.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm_networking.ASM.Constant.Requests.RegisterRequest;
import com.example.asm_networking.ASM.Constant.Respone.RegisterResponse;
import com.example.asm_networking.ASM.Constant.Retrofits.RetrofitBuilder;
import com.example.asm_networking.ASM.Constant.Retrofits.iRetrofitService;
import com.example.asm_networking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView tvLogin;
    EditText edt_userRegister, edt_passwordRegister;
    Button btn_register;

    private iRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvLogin = findViewById(R.id.tvLogin);
        edt_userRegister = findViewById(R.id.edt_userRegister);
        edt_passwordRegister = findViewById(R.id.edt_passwordRegister);
        btn_register = findViewById(R.id.btn_register);
        service = RetrofitBuilder.createService(iRetrofitService.class);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRegister(View view) {
        String email = edt_userRegister.getText().toString();
        String password = edt_passwordRegister.getText().toString();
        RegisterRequest request = new RegisterRequest(email, password);
        service.register(request).enqueue(register);
    }

    Callback<RegisterResponse> register = new Callback<RegisterResponse>() {
        @Override
        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
            if (response.isSuccessful()) {
                RegisterResponse registerResponse = response.body();
                finish();
            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            Log.d(">>> register", "onFailure: " + t.getMessage());
        }
    };
}