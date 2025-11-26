package com.by.chapter02.menu;

public class CartItem {
    private int menuId;
    private String name;
    private double price;
    private int quantity;

    public CartItem(int menuId, String name, double price, int quantity) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}