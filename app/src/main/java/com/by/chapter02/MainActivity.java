package com.by.chapter02;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.by.chapter02.menu.MenuActivity;
import com.by.chapter02.store.DBActivity;
import com.by.chapter02.store.StoreActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_db).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                startActivity(new Intent("com.by.second"));
                break;
            case R.id.btn_two:
                Intent it = new Intent();
                it.setAction(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://www.baidu.com"));
                startActivity(it);
                break;
            case R.id.btn_three:
                Intent it1 = new Intent(MainActivity.this, SecondActivity.class);
                it1.putExtra("name", "zhangs");
                startActivity(it1);
                break;
            case R.id.btn_four:
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
                break;
            case R.id.btn_five:
                startActivity(new Intent(MainActivity.this, StoreActivity.class));
                break;
            case R.id.btn_db:
                startActivity(new Intent(MainActivity.this,DBActivity.class));
                break;
        }

    }
}
