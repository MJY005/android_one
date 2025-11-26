package com.by.chapter02.menu;

import android.content.Context;
import android.view.*;
import android.widget.*;

import com.by.chapter02.R;

import java.util.*;

public class CartAdapter extends BaseAdapter {
    private List<CartItem> cartItems;
    private Context context;
    private OnRemoveListener listener;

    public interface OnRemoveListener {
        void onRemove(CartItem item);
    }

    public CartAdapter(Context context, List<CartItem> items, OnRemoveListener listener) {
        this.context = context;
        this.cartItems = items;
        this.listener = listener;
    }

    @Override
    public int getCount() { return cartItems.size(); }

    @Override
    public Object getItem(int position) { return cartItems.get(position); }

    @Override
    public long getItemId(int position) { return cartItems.get(position).getMenuId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItem item = cartItems.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        TextView tvName = view.findViewById(R.id.tvCartName);
        TextView tvPrice = view.findViewById(R.id.tvCartPrice);
        TextView tvQuantity = view.findViewById(R.id.tvCartQuantity);
        Button btnRemove = view.findViewById(R.id.btnCartRemove);

        tvName.setText(item.getName());
        tvPrice.setText("¥" + item.getPrice());
        tvQuantity.setText("数量：" + item.getQuantity());
        btnRemove.setOnClickListener(v -> listener.onRemove(item));
        return view;
    }

    public void updateData(List<CartItem> items) {
        this.cartItems = items;
        notifyDataSetChanged();
    }
}