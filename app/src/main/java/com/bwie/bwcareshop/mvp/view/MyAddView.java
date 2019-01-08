package com.bwie.bwcareshop.mvp.view;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface MyAddView<T> {
    void onSuccessed(T data);
    void onFailered(String msg);
}
