package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.HomeListBean;
import com.bwie.bwcareshop.mvp.callback.HomeCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.google.gson.Gson;

/**
 * author：张腾
 * date：2018/12/30
 */
public class HomeModel {

    public void showList(final HomeCallBack homeCallBack){
       new OkHttpUtil().OkHttpGet(Apis.HOME_LIST_URL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
           @Override
           public void OkHttpSuccess(String data) {
               Gson gson = new Gson();
               HomeListBean listBean = gson.fromJson(data, HomeListBean.class);
               HomeListBean.ResultBean resultBean = listBean.getResult();
               homeCallBack.onSuccessList(resultBean);
           }

           @Override
           public void OkHttpError(String error) {
               homeCallBack.onFalier(error);
           }
       });
    }
}
