package com.bwie.bwcareshop.mvp.callback;

import com.bwie.bwcareshop.bean.WalletBean;

/**
 * author：张腾
 * date：2018/12/28
 */
public interface MyWalletCallBack {
    void onSuccess(WalletBean walletBean);
    void onFailer(String msg);
}
