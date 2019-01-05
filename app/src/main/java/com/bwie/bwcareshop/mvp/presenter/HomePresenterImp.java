package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.bean.HomeListBean;
import com.bwie.bwcareshop.mvp.callback.HomeCallBack;
import com.bwie.bwcareshop.mvp.model.HomeModel;
import com.bwie.bwcareshop.mvp.view.HomeView;

/**
 * author：张腾
 * date：2018/12/30
 */
public class HomePresenterImp {

    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenterImp(HomeView homeView){
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    public void deach(){
        homeView=null;
        homeModel=null;
    }
    public void showList(){
        homeModel.showList(new HomeCallBack() {
            @Override
            public void onSuccessList(HomeListBean.ResultBean mList) {
                homeView.onSuccessList(mList);
            }

            @Override
            public void onFalier(String msg) {
                homeView.onFalier(msg);
            }
        });
    }
}
