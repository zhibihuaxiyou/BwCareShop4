package com.bwie.bwcareshop.mvp.view;

import com.bwie.bwcareshop.bean.HomeBannerBean;
import com.bwie.bwcareshop.bean.HomeListBean;

import java.util.List;

/**
 * author：张腾
 * date：2018/12/30
 */
public interface HomeView {
    void onSuccessList(HomeListBean.ResultBean mList);
    void onFalier(String msg);
}
