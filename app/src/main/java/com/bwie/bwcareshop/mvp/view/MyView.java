package com.bwie.bwcareshop.mvp.view;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface MyView<T> {
    void onSuccess(T data);
    void onFailer(String msg);
}
