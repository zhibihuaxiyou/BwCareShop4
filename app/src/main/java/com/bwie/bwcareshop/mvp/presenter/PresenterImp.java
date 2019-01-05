package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.model.ModelImp;
import com.bwie.bwcareshop.mvp.view.MyView;

import java.util.Map;

/**
 * author：张腾
 * date：2018/12/28
 */
public class PresenterImp {

    public ModelImp loginOrRegistModel;
    public MyView loginOrRegistView;

    public PresenterImp(MyView loginOrRegistView){
        this.loginOrRegistView = loginOrRegistView;
        loginOrRegistModel = new ModelImp();
    }

    public void loginOrRegist(String url, final Map<String, String> map, Class clazz){
        loginOrRegistModel.loginOrRegist(url, map, clazz, new MyCallBack() {
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
