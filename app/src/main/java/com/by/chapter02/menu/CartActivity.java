package com.by.chapter02.menu;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


import java.util.*;

public class CartActivity extends AppCompatActivity {

    private ListView lvCart;
    private TextView tvTotal;
    private Button btnCheckout;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCart = findViewById(R.id.lvCart);
        tvTotal = findViewById(R.id.tvCartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);

        CartDbHelper db = new CartDbHelper(this);
        cartItemList = db.getCartItems();

        cartAdapter = new CartAdapter(this, cartItemList, item -> {
            db.removeFromCart(item.getMenuId());
            refreshCart();
        });
        lvCart.setAdapter(cartAdapter);

        calculateTotal();

        btnCheckout.setOnClickListener(v -> {
            double total = calculateTotal();
            Toast.makeText(this, "结算成功，总价¥" + total, Toast.LENGTH_LONG).show();
            db.clearCart();
            refreshCart();
        });
    }

    private double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotal.setText("总价：¥" + total);
        return total;
    }

    private void refreshCart() {
        CartDbHelper db = new CartDbHelper(this);
        cartItemList = db.getCartItems();
        cartAdapter.updateData(cartItemList);
        calculateTotal();
    }
}