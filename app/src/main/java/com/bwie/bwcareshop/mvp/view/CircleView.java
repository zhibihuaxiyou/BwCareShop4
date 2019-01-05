package com.bwie.bwcareshop.mvp.view;

import com.bwie.bwcareshop.bean.CircleBean;

import java.util.List;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface CircleView {
    void onSuccess(List<CircleBean.Result> data);
    void onFailer(String msg);
}
