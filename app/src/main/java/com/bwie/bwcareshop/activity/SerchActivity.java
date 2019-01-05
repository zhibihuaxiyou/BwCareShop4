package com.bwie.bwcareshop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.adapter.SerchAdapter;
import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.presenter.SerchPresenter;
import com.bwie.bwcareshop.mvp.view.SerchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SerchActivity extends AppCompatActivity implements SerchView {

    @BindView(R.id.Im_menu)
    ImageView mImMenu;
    @BindView(R.id.edit_serch)
    EditText mEditSerch;
    @BindView(R.id.text_serch)
    TextView mTextSerch;
    @BindView(R.id.Im_serch)
    ImageView mImSerch;
    @BindView(R.id.relative)
    RelativeLayout mRelative;
    @BindView(R.id.recy_serch)
    RecyclerView mRecySerch;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.serch_layout)
    RelativeLayout mSerchLayout;
    @BindView(R.id.recyclerView)
    RelativeLayout mRecyclerView;
    private SerchPresenter serchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Im_menu, R.id.text_serch, R.id.Im_serch})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.Im_menu:
                break;
            case R.id.Im_serch:
                onSerchShow();
                break;
            case R.id.text_serch:
                onSerch();
                break;
        }
    }

    private void onSerchShow() {
        mImSerch.setVisibility(View.GONE);
        mTextSerch.setVisibility(View.VISIBLE);
        mEditSerch.setVisibility(View.VISIBLE);
    }

    private void onSerch() {
        String editSerch = mEditSerch.getText().toString();
        if (TextUtils.isEmpty(editSerch)) {
            mTextSerch.setVisibility(View.GONE);
            mEditSerch.setVisibility(View.GONE);
            mImSerch.setVisibility(View.VISIBLE);
        }else {
            serchPresenter = new SerchPresenter(this);
            mRecySerch.setVisibility(View.VISIBLE);
            mSerchLayout.setVisibility(View.GONE);
            serchPresenter.serch(editSerch,1,5);
        }

    }

    @Override
    public void onSuccess(List<SearchBean.Result> mList) {
        if (mList.size()>0) {
            mRecySerch.setVisibility(View.VISIBLE);
            mSerchLayout.setVisibility(View.GONE);
            SerchAdapter serchAdapter = new SerchAdapter(this, mList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecySerch.setLayoutManager(gridLayoutManager);
            mRecySerch.setAdapter(serchAdapter);
            serchAdapter.notifyDataSetChanged();
        }else {
            mRecySerch.setVisibility(View.GONE);
            mSerchLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFalierd(String msg) {

    }
}
