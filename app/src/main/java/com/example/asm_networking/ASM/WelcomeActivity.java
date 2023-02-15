package com.example.asm_networking.ASM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asm_networking.ASM.Activity.LoginActivity;
import com.example.asm_networking.R;

public class WelcomeActivity extends AppCompatActivity {
    Button bnt_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bnt_go = findViewById(R.id.bnt_go);
    }

    public void btngoClick(View view){
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }
}