package com.example.asm_networking.ASM.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asm_networking.ASM.Constant.Requests.RegisterRequest;
import com.example.asm_networking.ASM.Constant.Respone.RegisterResponse;
import com.example.asm_networking.ASM.Constant.Retrofits.RetrofitBuilder;
import com.example.asm_networking.ASM.Constant.Retrofits.iRetrofitService;
import com.example.asm_networking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edt_user, edt_password;
    Button btn_login;
    TextView tvRegister, tvForgotPassword;
    private iRetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        edt_user = findViewById(R.id.edt_user);
        edt_password = findViewById(R.id.edt_password);
        service = RetrofitBuilder.createService(iRetrofitService.class);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onForgotPassword(view);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin(view);
            }
        });
    }

    public void onLogin(View view) {
        String email = edt_user.getText().toString();
        String password = edt_password.getText().toString();
        RegisterRequest request = new RegisterRequest(email, password);
        service.login(request).enqueue(login);
    }

    Callback<RegisterResponse> login = new Callback<RegisterResponse>() {
        @Override
        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
            if (response.isSuccessful()) {
                RegisterResponse registerResponse = response.body();
                if (registerResponse.getResult()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

    public void onForgotPassword(View view) {
        //bấm nút chuyển sang màn hình Forgot, nhập email, gọi API
        //hoặc bấm nút hiện ra dialog để nhập email
//        Log.d(">>>>>>", "Quên mật khẩu");
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}