package com.bwie.bwcareshop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.fragment.CircleFragment;
import com.bwie.bwcareshop.fragment.HomeFragment;
import com.bwie.bwcareshop.fragment.ListFragment;
import com.bwie.bwcareshop.fragment.MyFragment;
import com.bwie.bwcareshop.fragment.ShoppingCartFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(34,34)
                .setTabPadding(20,10,10)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("首页", R.mipmap.common_tab_btn_home_s,R.mipmap.common_tab_btn_home_n, HomeFragment.class)
                .addTabItem("圈子", R.mipmap.common_tab_btn_circle_s,R.mipmap.common_tab_btn_circle_n, CircleFragment.class)
                .setImgSize(80,80)
                .setTabPadding(0,10,10)
                .addTabItem("购物车", R.mipmap.commom_tab_btn_shopping_cart_n, R.mipmap.inco_shopping_care_s, ShoppingCartFragment.class)
                .setImgSize(34,34)
                .setTabPadding(20,10,10)
                .addTabItem("订单", R.mipmap.common_tab_btn_list_s, R.mipmap.common_tab_btn_list_n, ListFragment.class)
                .addTabItem("我的", R.mipmap.common_tab_btn_my_s, R.mipmap.common_tab_btn_my_n, MyFragment.class);
    }

    private void initView() {
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottomTabBar);
    }
}
