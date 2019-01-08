package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.model.DetailsModel;
import com.bwie.bwcareshop.mvp.view.MyView;

/**
 * author：张腾
 * date：2019/1/7
 */
public class DetailsPresenter {
    private DetailsModel detailsModel;
    private MyView myView;

    public DetailsPresenter(MyView myView){
        this.myView = myView;
        detailsModel = new DetailsModel();
    }

    public void details(int commodityId, final Class clazz){
        detailsModel.details(commodityId, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                myView.onSuccess(data);
            }

            @Override
            public void onFailer(String msg) {
                myView.onFailer(msg);
            }
        });
    }
}
