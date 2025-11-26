package com.by.chapter02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 读取通过 putExtra 传递的 "name" 数据
        String name = getIntent().getStringExtra("name");
        if (name != null) { // 避免空指针
            Toast.makeText(SecondActivity.this, name, Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        String name = getIntent().getData().toString();
//        Toast.makeText(SecondActivity.this,name,Toast.LENGTH_SHORT).show();
//
//    }
}
