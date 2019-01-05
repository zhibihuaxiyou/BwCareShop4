package com.bwie.bwcareshop.mvp.view;

import com.bwie.bwcareshop.bean.WalletBean;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface MyWalletView{
    void onSuccess(WalletBean walletBean);
    void onFailer(String msg);
}
