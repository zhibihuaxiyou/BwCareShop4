package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.mvp.callback.LoginAndRegistCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;

/**
 * author：张腾
 * date：2018/12/28
 */
public class LoginAndRegistModel {
    public void login(String phone, String pwd, final LoginAndRegistCallBack loginAndRegistCallBack){
        String LOGINURL = "http://172.17.8.100/small/user/v1/login";
    }
}
