package com.by.chapter02.menu;

import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<MenuItem> menuList;
    private OnMenuItemClickListener listener;

    public interface OnMenuItemClickListener {
        void onClick(MenuItem item);
    }

    public MenuAdapter(List<MenuItem> menuList, OnMenuItemClickListener listener) {
        this.menuList = menuList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem item = menuList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("¥" + item.getPrice());
        holder.ivPic.setImageResource(item.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onClick(item));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivPic;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMenuName);
            tvPrice = itemView.findViewById(R.id.tvMenuPrice);
            ivPic = itemView.findViewById(R.id.ivMenuPic);
        }
    }
}