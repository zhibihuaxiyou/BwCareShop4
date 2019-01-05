package com.bwie.bwcareshop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.fragment.CircleFragment;
import com.bwie.bwcareshop.fragment.HomeFragment;
import com.bwie.bwcareshop.fragment.ListFragment;
import com.bwie.bwcareshop.fragment.MyFragment;
import com.bwie.bwcareshop.fragment.ShoppingCartFragment;
import com.bwie.bwcareshop.utils.AddEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private RadioGroup group;
    //创建一个空的集合
    private List<Fragment> mfragmentList = new ArrayList<>();
    private Fragment currFragment;
    private FragmentManager mfragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        pager = (ViewPager) findViewById(R.id.show_viewpager);
        group = (RadioGroup) findViewById(R.id.Rg_group);
        //预加载
        pager.setOffscreenPageLimit(4);

        //注册eventBus
        EventBus.getDefault().register(this);

        //创建集合
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CircleFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new ListFragment());
        fragments.add(new MyFragment());

        //适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        //让viewpager与按钮同步
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());
                clearAll(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Rb_home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.Rb_cricle:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.Rb_shop:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.Rb_list:
                        pager.setCurrentItem(3);
                        break;
                    case R.id.Rb_my:
                        pager.setCurrentItem(4);
                        break;
                }
            }
        });

        mfragmentManager = getSupportFragmentManager();

    }

    //添加fragment
    @Subscribe
    public void eventAdd(AddEvent event) {
        if (pager.getVisibility() != View.GONE) {
            pager.setVisibility(View.GONE);
        }
        FragmentTransaction transaction = mfragmentManager.beginTransaction();
        if (currFragment != null) {
            transaction.hide(currFragment);
        }
        currFragment = event.getNeedAdd();
        mfragmentList.add(currFragment);
        transaction.add(R.id.frameLayout, currFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mfragmentList.remove(currFragment);
        currFragment = null;
        if (mfragmentList.size() > 0) {
            currFragment = mfragmentList.get(mfragmentList.size() - 1);
        } else if (pager.getVisibility() != View.VISIBLE) {
            pager.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe
    public void eventDismiss(DismissEvent event) {
        Fragment needDismiss = event.getNeedDismiss();
        mfragmentList.remove(needDismiss);
        currFragment = null;
        if (mfragmentList.size() > 0) {
            currFragment = mfragmentList.get(mfragmentList.size() - 1);
        } else if (pager.getVisibility() != View.VISIBLE) {
            pager.setVisibility(View.VISIBLE);
        }
        mfragmentManager.popBackStack();
    }

    //清除
    public void clearAll(int position) {

        for (int i = 0; i < mfragmentList.size(); i++) {
            mfragmentManager.popBackStack();
        }
        currFragment = null;
        mfragmentList.clear();
        if (pager.getVisibility() != View.VISIBLE) {
            pager.setVisibility(View.VISIBLE);
        }
        pager.setCurrentItem(position);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
