package com.bwie.bwcareshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.InformationBean;
import com.bwie.bwcareshop.mvp.presenter.InformationPresenter;
import com.bwie.bwcareshop.mvp.view.MyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.icon_info)
    ImageView mIconInfo;
    @BindView(R.id.text_name)
    TextView mTextName;
    @BindView(R.id.text_pwd)
    TextView mTextPwd;
    private InformationPresenter informationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        informationPresenter = new InformationPresenter(this);
        informationPresenter.showInformation(Apis.GOODS_USER_URL, InformationBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        InformationBean.Result result = (InformationBean.Result) data;
        Glide.with(this).load(result.getHeadPic()).into(mIconInfo);
        mTextName.setText(result.getNickName());
        mTextPwd.setText(result.getPassword());
    }

    @Override
    public void onFailer(String msg) {

    }
}
