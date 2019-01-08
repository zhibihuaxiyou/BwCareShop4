package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.netWork.RetrofitManagerUtil;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * author：张腾
 * date：2018/12/28
 */
public class AddShopModelImp {
    public void addShop(String url, Map<String, String> map, final Class clazz, final MyCallBack myCallBack){
        Map<String, RequestBody> mMap = RetrofitManagerUtil.getInstance().generateRequestBody(map);
        RetrofitManagerUtil.getInstance().put(url,mMap,new RetrofitManagerUtil.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (myCallBack != null) {
                        myCallBack.onSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (myCallBack != null) {
                        myCallBack.onFailer(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailed(String error) {
                if (myCallBack != null) {
                    myCallBack.onFailer(error);
                }
            }
        });
    }
}
