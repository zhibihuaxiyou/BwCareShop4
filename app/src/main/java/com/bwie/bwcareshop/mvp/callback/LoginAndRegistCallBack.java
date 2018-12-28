package com.bwie.bwcareshop.mvp.callback;

import com.bwie.bwcareshop.bean.LoginAndRegistBean;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface LoginAndRegistCallBack {
    void onSuccess(LoginAndRegistBean loginAndRegistBean);
    void onFailer(String msg);
}
