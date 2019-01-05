package com.bwie.bwcareshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.bean.HomeBannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author：张腾
 * date：2018/12/29
 */
public class BannerAdapter extends PagerAdapter{
    private List<HomeBannerBean.Result> mResultBanner = new ArrayList<>();
    private Context mContext;

    public BannerAdapter( Context mContext,List<HomeBannerBean.Result> mResultBanner) {
        this.mResultBanner = mResultBanner;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int p = position % mResultBanner.size();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_item,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(mResultBanner.get(p).getImageUrl()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
