package com.bwie.bwcareshop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.WalletBean;
import com.bwie.bwcareshop.mvp.presenter.MyWalletPresenter;
import com.bwie.bwcareshop.mvp.view.MyView;
import com.bwie.bwcareshop.mvp.view.MyWalletView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends AppCompatActivity implements MyWalletView {

    @BindView(R.id.tv_wallet)
    TextView mTvWallet;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        map = new HashMap<>();
        map.put("page",1+"");
        map.put("count",5+"");
        MyWalletPresenter myPresenter = new MyWalletPresenter(this);
        myPresenter.show(1,3);
    }


    @Override
    public void onSuccess(WalletBean walletBean) {
        mTvWallet.setText(walletBean.getResult().getBalance()+"");
    }

    @Override
    public void onFailer(String msg) {

    }
}
