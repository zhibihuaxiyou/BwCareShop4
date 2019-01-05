package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.bean.WalletBean;
import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.callback.MyWalletCallBack;
import com.bwie.bwcareshop.mvp.model.MyWalletModel;
import com.bwie.bwcareshop.mvp.view.MyView;
import com.bwie.bwcareshop.mvp.view.MyWalletView;

import java.util.Map;

/**
 * author：张腾
 * date：2019/1/4
 */
public class MyWalletPresenter {
    private MyWalletView myWalletView;
    private MyWalletModel myModel;
    public MyWalletPresenter(MyWalletView myWalletView){
        this.myWalletView = myWalletView;
        myModel = new MyWalletModel();
    }

    public void show(int page, int count){
        myModel.showWallet(page, count, new MyWalletCallBack() {
            @Override
            public void onSuccess(WalletBean walletBean) {
                myWalletView.onSuccess(walletBean);
            }

            @Override
            public void onFailer(String msg) {
                myWalletView.onFailer(msg);
            }
        });
    }
}
