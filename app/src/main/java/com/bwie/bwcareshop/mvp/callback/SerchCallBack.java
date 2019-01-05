package com.bwie.bwcareshop.mvp.callback;

import com.bwie.bwcareshop.bean.SearchBean;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public interface SerchCallBack {
    void onSuccess(List<SearchBean.Result> mList);
    void onFalierd(String msg);
}
