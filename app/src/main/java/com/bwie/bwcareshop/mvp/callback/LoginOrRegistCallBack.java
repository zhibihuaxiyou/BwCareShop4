package com.bwie.bwcareshop.mvp.callback;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface LoginOrRegistCallBack<T> {
    void onSuccess(T data);
    void onFailer(String msg);
}
