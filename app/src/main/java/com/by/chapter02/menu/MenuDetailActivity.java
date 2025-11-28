package com.by.chapter02.menu;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;



public class MenuDetailActivity extends AppCompatActivity {

    private TextView tvName, tvDesc, tvPrice;
    private ImageView ivPic;
    private Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvDetailName);
        tvDesc = findViewById(R.id.tvDetailDesc);
        tvPrice = findViewById(R.id.tvDetailPrice);
        ivPic = findViewById(R.id.ivDetailPic);
        btnAddCart = findViewById(R.id.btnAddCart);

        int menuId = getIntent().getIntExtra("MENU_ID", 0);
        MenuItem item = MenuDatabase.getMenuItemById(menuId); // 静态模拟菜单数据

        tvName.setText(item.getName());
        tvDesc.setText(item.getDescription());
        tvPrice.setText("¥" + item.getPrice());
        ivPic.setImageResource(item.getImageResId());

        btnAddCart.setOnClickListener(v -> {
            CartDbHelper db = new CartDbHelper(MenuDetailActivity.this);
            db.addToCart(item);
            Toast.makeText(this, "已加入购物车", Toast.LENGTH_SHORT).show();
        });
    }
}