package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.callback.SerchCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public class SerchModel {
    public void serch(String keyword, int page, int count, final SerchCallBack serchCallBack){
        String SERCHURL = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword?keyword="+keyword+"&page="+page+"&count="+count;
        new OkHttpUtil().OkHttpGet(SERCHURL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                SearchBean searchBean = gson.fromJson(data, SearchBean.class);
                List<SearchBean.Result> resultList = searchBean.getResult();
                if (resultList!=null) {
                    serchCallBack.onSuccess(resultList);
                }
            }

            @Override
            public void OkHttpError(String error) {
                serchCallBack.onFalierd(error);
            }
        });
    }
}
