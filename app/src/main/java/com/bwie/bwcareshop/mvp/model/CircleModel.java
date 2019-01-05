package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.bean.CircleBean;
import com.bwie.bwcareshop.mvp.callback.CircleCallBack;
import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/4
 */
public class CircleModel {
    public void showCircle(int page, int count, final CircleCallBack myCallBack){
        String CIRCLEURL = "http://mobile.bwstudent.com/small/circle/v1/findCircleList?page="+page+"&count="+count;
        new OkHttpUtil().OkHttpGet(CIRCLEURL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                CircleBean circleBean = gson.fromJson(data, CircleBean.class);
                List<CircleBean.Result> resultList = circleBean.getResult();
                if (resultList!=null) {
                    myCallBack.onSuccess(resultList);
                }
            }

            @Override
            public void OkHttpError(String error) {
                myCallBack.onFailer(error);
            }
        });
    }
}
