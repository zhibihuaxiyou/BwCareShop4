package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.callback.SerchCallBack;
import com.bwie.bwcareshop.mvp.model.SerchModel;
import com.bwie.bwcareshop.mvp.view.SerchView;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public class SerchPresenter {
    private SerchModel serchModel;
    private SerchView serchView;
    public SerchPresenter(SerchView serchView){
        this.serchView = serchView;
        serchModel = new SerchModel();
    }

    public void deach(){
        serchModel=null;
        serchView=null;
    }

    public void serch(String keyword, int page, int count){
        serchModel.serch(keyword, page, count, new SerchCallBack() {
            @Override
            public void onSuccess(List<SearchBean.Result> mList) {
                serchView.onSuccess(mList);
            }

            @Override
            public void onFalierd(String msg) {
                serchView.onFalierd(msg);
            }
        });
    }
}
