package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.netWork.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * author：张腾
 * date：2018/12/28
 */
public class ModelImp {
    public void loginOrRegist(String url, Map<String, String> map, final Class clazz, final MyCallBack loginOrRegistCallBack){
        Map<String, RequestBody> mMap = RetrofitManager.getManager().getRequestBody(map);
        RetrofitManager.getManager().postFormBody(url,mMap).setHttpListener(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (loginOrRegistCallBack != null) {
                        loginOrRegistCallBack.onSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (loginOrRegistCallBack != null) {
                        loginOrRegistCallBack.onFailer(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailed(String error) {
                if (loginOrRegistCallBack != null) {
                    loginOrRegistCallBack.onFailer(error);
                }
            }
        });
    }
}
