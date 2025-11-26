package com.by.chapter02.menu;

import android.content.*;
import android.database.sqlite.*;
import android.database.*;
import java.util.*;

public class CartDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cart.db";
    private static final int DB_VERSION = 1;

    public CartDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cart(menuId INTEGER PRIMARY KEY, name TEXT, price REAL, quantity INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS cart");
        onCreate(db);
    }

    // 添加到购物车（若已存在则数量+1）
    public void addToCart(MenuItem item) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT quantity FROM cart WHERE menuId=?", new String[]{String.valueOf(item.getId())});
        if (cursor.moveToFirst()) {
            int quantity = cursor.getInt(0) + 1;
            db.execSQL("UPDATE cart SET quantity=? WHERE menuId=?", new Object[]{quantity, item.getId()});
        } else {
            db.execSQL("INSERT INTO cart(menuId, name, price, quantity) VALUES(?,?,?,1)",
                    new Object[]{item.getId(), item.getName(), item.getPrice()});
        }
        cursor.close();
    }

    // 获取所有购物车商品
    public List<CartItem> getCartItems() {
        List<CartItem> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT menuId, name, price, quantity FROM cart", null);
        while(cursor.moveToNext()) {
            list.add(new CartItem(cursor.getInt(0), cursor.getString(1),
                    cursor.getDouble(2), cursor.getInt(3)));
        }
        cursor.close();
        return list;
    }

    // 删除购物车商品
    public void removeFromCart(int menuId) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM cart WHERE menuId=?", new Object[]{menuId});
    }

    // 清空购物车
    public void clearCart() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM cart");
    }
}