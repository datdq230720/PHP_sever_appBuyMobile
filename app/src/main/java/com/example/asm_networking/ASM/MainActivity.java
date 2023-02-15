package com.example.asm_networking.ASM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.asm_networking.ASM.Models.Dog;
import com.example.asm_networking.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_dog;
    RecyclerView rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn_dog = findViewById(R.id.btn_dog);
//        rcv = findViewById(R.id.rcv_list);
//
//        dogAdapter = new DogAdapter(MainActivity.this);
//
//        dogAdapter.setDataDog(getListUser());
//        Log.d(">>>>", "getListUser: "+getListUser());
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rcv.setLayoutManager(llm);
//        rcv.setAdapter(dogAdapter);
//
//    }
//
//    public void btn_dogClick (View view){
//        Intent i = new Intent(MainActivity.this, DetailActivity.class);
//        startActivity(i);
//    }

        }



}