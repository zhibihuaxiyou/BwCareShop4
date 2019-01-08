package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.google.gson.Gson;

/**
 * author：张腾
 * date：2019/1/7
 */
public class DetailsModel {
    public void details(int commodityId, final Class clazz, final MyCallBack myCallBack){
       String DETAILS_URL = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityDetailsById?commodityId="+commodityId;
        /* RetrofitManager.getManager().get(url).setHttpListener(new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (myCallBack!=null) {
                    myCallBack.onSuccess(o);
                }
            }

            @Override
            public void onFailed(String error) {
                if (myCallBack!=null) {
                    myCallBack.onFailer(error);
                }
            }
        });*/
        OkHttpUtil.getInstance().OkHttpGet(DETAILS_URL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (myCallBack!=null) {
                    myCallBack.onSuccess(o);
                }
            }

            @Override
            public void OkHttpError(String error) {
                if (myCallBack!=null) {
                    myCallBack.onFailer(error);
                }
            }
        });
    }
}
