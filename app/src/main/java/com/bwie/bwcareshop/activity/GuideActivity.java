package com.bwie.bwcareshop.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.adapter.GuidePageAdapter;
import com.bwie.bwcareshop.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现首次启动的引导页面
 */
public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点

    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;

    //最后一页的按钮
    private Button mBtnGuide;
    private SharedPreferences ps;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        mBtnGuide = (Button) findViewById(R.id.btn_guide);
        //设置SharedPreferences 判断 是否第一次登陆
        ps = getSharedPreferences("config", MODE_PRIVATE);
        isFirst = ps.getBoolean("isFirst", false);
        mBtnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ps.edit().putBoolean("isFirst", true).commit();
                onIntent();
            }
        });

        //加载ViewPager
        initViewPager();
        //加载底部圆点
        initPoint();
        /**
         * 如果不是第一次登陆的话 直接跳转到首页
         */
        if (isFirst) {
            onIntent();
        }
    }

    private void onIntent() {
        IntentUtils.getInstence().intent(GuideActivity.this, MainActivity.class);
        finish();
    }

    /**
     * 加载底部圆点
     */
    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(40, 10));
            iv_point.setPadding(20, 10, 20, 10);//left,top,right,bottom
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.drawable.shape_circle_shi);
            } else {
                iv_point.setBackgroundResource(R.drawable.shape_circle_kong);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }


    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.inco_guide1, R.drawable.inco_guide2, R.drawable.inco_guide3, R.drawable.inco_guide4, R.drawable.inco_guide5};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);

            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0; i < length; i++) {
            ivPointArray[position].setBackgroundResource(R.drawable.shape_circle_shi);
            if (position != i) {
                ivPointArray[i].setBackgroundResource(R.drawable.shape_circle_kong);
            }
        }

        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1) {
            mBtnGuide.setVisibility(View.VISIBLE);
        } else {
            mBtnGuide.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
