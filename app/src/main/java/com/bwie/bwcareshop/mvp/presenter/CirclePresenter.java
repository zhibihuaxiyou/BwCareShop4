package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.bean.CircleBean;
import com.bwie.bwcareshop.mvp.callback.CircleCallBack;
import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.model.CircleModel;
import com.bwie.bwcareshop.mvp.view.CircleView;
import com.bwie.bwcareshop.mvp.view.MyView;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/4
 */
public class CirclePresenter {
    private CircleView myView;
    private CircleModel circleModel;

    public CirclePresenter(CircleView myView){
        this.myView = myView;
        circleModel = new CircleModel();
    }

    public void showCircle(int page,int count){
        circleModel.showCircle(page, count, new CircleCallBack() {


            @Override
            public void onSuccess(List<CircleBean.Result> data) {
                myView.onSuccess(data);
            }

            @Override
            public void onFailer(String msg) {
                myView.onFailer(msg);
            }
        });
    }
}
