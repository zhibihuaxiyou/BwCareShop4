package com.bwie.bwcareshop.mvp.model;

import com.bwie.bwcareshop.bean.MoreBean;
import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.callback.MoreCallBack;
import com.bwie.bwcareshop.mvp.callback.SerchCallBack;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public class MoreModel {
    public void showMore(String labelId, int page, int count, final MoreCallBack moreCallBack){
        String LABELIDURL = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityListByLabel?labelId="+labelId+"&page="+page+"&count="+count;
        new OkHttpUtil().OkHttpGet(LABELIDURL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                MoreBean moreBean = gson.fromJson(data, MoreBean.class);
                List<MoreBean.ResultBean> resultList = moreBean.getResult();
                if (resultList!=null) {
                    moreCallBack.onSuccessMore(resultList);
                }
            }

            @Override
            public void OkHttpError(String error) {
                moreCallBack.onFalierd(error);
            }
        });
    }
}
