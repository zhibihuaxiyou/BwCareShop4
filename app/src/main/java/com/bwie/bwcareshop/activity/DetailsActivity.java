package com.bwie.bwcareshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.AddShopBean;
import com.bwie.bwcareshop.bean.DetailsBean;
import com.bwie.bwcareshop.mvp.presenter.AddShopPresenterImp;
import com.bwie.bwcareshop.mvp.presenter.DetailsPresenter;
import com.bwie.bwcareshop.mvp.view.MyAddView;
import com.bwie.bwcareshop.mvp.view.MyView;
import com.bwie.bwcareshop.utils.ToastUtils;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements MyView, MyAddView {

    @BindView(R.id.icon_finish)
    ImageView mIconFinish;
    @BindView(R.id.flyBanner)
    FlyBanner mFlyBanner;
    @BindView(R.id.text_price)
    TextView mTextPrice;
    @BindView(R.id.text_commodityName)
    TextView mTextCommodityName;
    @BindView(R.id.text_weight)
    TextView mTextWeight;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.icon_add_shop)
    ImageView mIconAddShop;
    private DetailsPresenter detailsPresenter;
    private AddShopPresenterImp addShopPresenterImp;
    private String commodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        commodityId = intent.getStringExtra("commodityId");
        int id = Integer.parseInt(commodityId);
        detailsPresenter = new DetailsPresenter(this);
        detailsPresenter.details(id, DetailsBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        DetailsBean detailsBean = (DetailsBean) data;
        DetailsBean.ResultBean resultBean = detailsBean.getResult();
        List<String> list = new ArrayList<>();
        mTextPrice.setText("￥ " + resultBean.getPrice() + "");
        mTextCommodityName.setText(resultBean.getCommodityName());
        mTextWeight.setText("重量 " + resultBean.getWeight() + "kg");
        String picture = resultBean.getPicture();
        String[] split = picture.split("\\,");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        mFlyBanner.setImagesUrl(list);
        mWebView.loadDataWithBaseURL(null, resultBean.getDetails(), "text/html", "UTF-8", null);

    }


    @Override
    public void onFailer(String msg) {

    }

    @OnClick({R.id.icon_finish, R.id.icon_add_shop})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.icon_finish:
                finish();
                break;
            case R.id.icon_add_shop:
                addShopPresenterImp = new AddShopPresenterImp(this);
                Map<String,String> map = new HashMap<>();
                map.put("data","[{\"commodityId\":"+commodityId+",\"count\":1}]");
                addShopPresenterImp.addShop(Apis.ADDSHOP_URL,map, AddShopBean.class);
                break;

        }
    }

    @Override
    public void onSuccessed(Object data) {
        AddShopBean addShopBean = (AddShopBean) data;
        if (addShopBean.getStatus().equals("0000")) {
            ToastUtils.showToast(this,addShopBean.getMessage());
        }
    }

    @Override
    public void onFailered(String msg) {
        ToastUtils.showToast(this,msg);
    }
}
