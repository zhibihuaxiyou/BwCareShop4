package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.model.InformationModel;
import com.bwie.bwcareshop.mvp.view.MyView;

/**
 * author：张腾
 * date：2019/1/4
 */
public class InformationPresenter {
    private MyView myView;
    private InformationModel informationModel;
    public InformationPresenter(MyView myView){
        this.myView = myView;
        informationModel = new InformationModel();
    }

    public void showInformation(String url,Class clazz){
        informationModel.showInformation(url, clazz, new MyCallBack() {
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
