//package com.by.chapter02.menu;
//
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.graphics.Color;
//import android.os.Bundle;
//
//import android.view.View;
//import android.widget.TextView;
//
//import com.by.chapter02.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MenuActivity extends AppCompatActivity {
//    private FragmentManager fragmentManager;
//    private FragmentTransaction fragmentTransaction;
//    private LeftFragment leftFragment;
//    private TextView tv_recommend, tv_must_buy;
//    private RightFragment rightFragment;
//    //推荐菜单列表数据
//    private String[] names1 = {"爆款*肥牛鱼豆腐骨肉相连三荤五素一份米饭", "豪华双人套餐",
//            "【热销】双人套餐（含两份米饭）"};
//    private String[] sales1 = {"月售520 好评度80%", "月售184 好评度68%",
//            "月售114 好评度60%"};
//    private String[] prices1 = {"￥23", "￥41", "￥32"};
//    private int[] imgs1 = {R.drawable.recom_one, R.drawable.recom_two,
//            R.drawable.recom_three};
//    //进店必买菜单列表数据
//    private String[] names2 = {"'蔬菜主义'1人套餐", "2人经典套餐", "3人经典套餐"};
//    private String[] sales2 = {"月售26 好评度70%", "月售12 好评度50%",
//            "月售4 好评度40%"};
//    private String[] prices2 = {"￥44", "￥132", "￥180"};
//    private int[] imgs2 = {R.drawable.must_buy_one, R.drawable.must_buy_two,
//            R.drawable.must_buy_three};
//    private Map<String, List<FoodBean>> map;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//        setData();
//        init();
//        clickEvent();
//    }
//
//    private void init() {
//        fragmentManager = getFragmentManager();//获取fragmentManager
//        //通过findFragmentById()方法获取leftFragment
//        leftFragment = (LeftFragment) fragmentManager.findFragmentById(R.id.left);
//        //获取左侧菜单栏中的控件
//        tv_recommend = leftFragment.getView().findViewById(R.id.tv_recommend);
//        tv_must_buy = leftFragment.getView().findViewById(R.id.tv_must_buy);
//    }
//
//    private void setData() {
//        map = new HashMap<>();
//        List<FoodBean> list1 = new ArrayList<>();
//        List<FoodBean> list2 = new ArrayList<>();
//        for (int i = 0; i < names1.length; i++) {
//            FoodBean bean = new FoodBean();
//            bean.setName(names1[i]);
//            bean.setSales(sales1[i]);
//            bean.setPrice(prices1[i]);
//            bean.setImg(imgs1[i]);
//            list1.add(bean);
//        }
//        map.put("1", list1);//将推荐菜单列表的数据添加到map集合中
//        for (int i = 0; i < names2.length; i++) {
//            FoodBean bean = new FoodBean();
//            bean.setName(names2[i]);
//            bean.setSales(sales2[i]);
//            bean.setPrice(prices2[i]);
//            bean.setImg(imgs2[i]);
//            list2.add(bean);
//        }
//        map.put("2", list2); //将进店必买菜单列表的数据添加到map集合中
//    }
//
//    private void clickEvent() {
//        tv_recommend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //调用switchData()方法填充Rightfragment中的数据
//                switchData(map.get("1"));
//                tv_recommend.setBackgroundColor(Color.WHITE);
//                tv_must_buy.setBackgroundResource(R.color.gray);
//            }
//        });
//        tv_must_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchData(map.get("2"));
//                tv_must_buy.setBackgroundColor(Color.WHITE);
//                tv_recommend.setBackgroundResource(R.color.gray);
//            }
//        });
//        //设置首次进入界面后，默认需要显示的数据
//        switchData(map.get("1"));
//    }
//
//    /**
//     * 填充Activity右侧的Fragment，并传递列表数据list
//     */
//    public void switchData(List<FoodBean> list) {
//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
//        //通过调用getInstance()方法实例化RightFragment
//        rightFragment = new RightFragment().getInstance(list);
//        //调用replace()方法
//        fragmentTransaction.replace(R.id.right, rightFragment);
//        fragmentTransaction.commit();
//    }
//}
//
package com.by.chapter02.menu;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.*;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 构建菜单列表
        menuList = Arrays.asList(
                new MenuItem(1, "红烧牛肉面", "经典牛肉面，香辣可口", 18.0, R.drawable.noodle),
                new MenuItem(2, "卤肉饭", "台湾风味，软糯入味", 15.0, R.drawable.rice),
                new MenuItem(3, "鸡蛋灌饼", "早餐优选，外酥里嫩", 8.0, R.drawable.egg_pie)
                // 可以继续添加菜单项
        );
        menuAdapter = new MenuAdapter(menuList, menuItem -> {
            // 点击后跳转详情页
            Intent intent = new Intent(MenuActivity.this, MenuDetailActivity.class);
            intent.putExtra("MENU_ID", menuItem.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(menuAdapter);

        // 购物车图标点击
        findViewById(R.id.btnCart).setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, CartActivity.class));
        });
    }
}