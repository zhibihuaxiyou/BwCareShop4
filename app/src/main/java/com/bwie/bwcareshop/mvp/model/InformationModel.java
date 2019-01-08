package com.bwie.bwcareshop.mvp.model;

import android.util.Log;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.netWork.RetrofitManagerUtil;
import com.google.gson.Gson;

/**
 * author：张腾
 * date：2019/1/4
 */
public class InformationModel {
    public void showInformation(String url,final Class clazz, final MyCallBack myCallBack){
        RetrofitManagerUtil.getInstance().get(url,new RetrofitManagerUtil.HttpListener() {
            @Override
            public void onSuccess(String data) {
                    try {
                        Object o = new Gson().fromJson(data, clazz);
                        if (myCallBack != null) {
                            myCallBack.onSuccess(o);
                        }
                    }catch (Exception  e){
                        Log.d("sssssssssssss", "onFailed: "+e);
                    }
                }

            @Override
            public void onFailed(String error) {
                Log.d("sssssssssssss", "onFailed: "+error);
                if (myCallBack != null) {
                    myCallBack.onSuccess(error);
                }
            }
        });
    }
}
