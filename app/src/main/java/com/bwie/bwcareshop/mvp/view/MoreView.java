package com.bwie.bwcareshop.mvp.view;

import com.bwie.bwcareshop.bean.MoreBean;
import com.bwie.bwcareshop.bean.SearchBean;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public interface MoreView<T> {
    void onSuccessMore(T mList);
    void onFalierd(String msg);
}
