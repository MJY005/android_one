package com.by.chapter02.menu;

import java.util.*;

public class MenuDatabase {
    private static List<MenuItem> menus = Arrays.asList(
            new MenuItem(1, "红烧牛肉面", "经典牛肉面，香辣可口", 18.0, R.drawable.noodle),
            new MenuItem(2, "卤肉饭", "台湾风味，软糯入味", 15.0, R.drawable.rice),
            new MenuItem(3, "鸡蛋灌饼", "早餐优选，外酥里嫩", 8.0, R.drawable.egg_pie)
            // ...
    );

    public static MenuItem getMenuItemById(int id) {
        for (MenuItem m : menus) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public static List<MenuItem> getAllMenus() {
        return menus;
    }
}