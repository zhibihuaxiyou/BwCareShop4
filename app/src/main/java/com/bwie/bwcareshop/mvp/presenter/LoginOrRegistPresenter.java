package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.mvp.callback.LoginOrRegistCallBack;
import com.bwie.bwcareshop.mvp.model.LoginOrRegistModel;
import com.bwie.bwcareshop.mvp.view.LoginOrRegistView;

import java.util.Map;

/**
 * author：张腾
 * date：2018/12/28
 */
public class LoginOrRegistPresenter {

    public LoginOrRegistModel loginOrRegistModel;
    public LoginOrRegistView loginOrRegistView;

    public LoginOrRegistPresenter(LoginOrRegistView loginOrRegistView){
        this.loginOrRegistView = loginOrRegistView;
        loginOrRegistModel = new LoginOrRegistModel();
    }

    public void loginOrRegist(String url, final Map<String, String> map, Class clazz){
        loginOrRegistModel.loginOrRegist(url, map, clazz, new LoginOrRegistCallBack() {
            @Override
            public void onSuccess(Object data) {
                loginOrRegistView.onSuccess(data);
            }

            @Override
            public void onFailer(String msg) {
                loginOrRegistView.onFailer(msg);
            }
        });
    }
}
