package com.bwie.bwcareshop.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.adapter.HomeMoliAdapter;
import com.bwie.bwcareshop.adapter.HomePinzhiAdapter;
import com.bwie.bwcareshop.adapter.HomeRexiaoAdapter;
import com.bwie.bwcareshop.adapter.SerchAdapter;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.HomeBannerBean;
import com.bwie.bwcareshop.bean.HomeListBean;
import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.presenter.HomePresenterImp;
import com.bwie.bwcareshop.mvp.presenter.SerchPresenter;
import com.bwie.bwcareshop.mvp.view.HomeView;
import com.bwie.bwcareshop.mvp.view.SerchView;
import com.bwie.bwcareshop.utils.IntentUtils;
import com.bwie.bwcareshop.utils.OkHttpUtil;
import com.bwie.bwcareshop.utils.ToastUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements HomeView, SerchView {

    @BindView(R.id.Im_menu)
    ImageView mImMenu;
    @BindView(R.id.text_serch)
    TextView mTextSerch;
    @BindView(R.id.text_mlss)
    TextView mTextMlss;
    @BindView(R.id.text_pzsh)
    TextView mTextPzsh;
    @BindView(R.id.text_rxsp)
    TextView mTextRxsp;
    @BindView(R.id.Im_serch)
    ImageView mImSerch;
    @BindView(R.id.edit_serch)
    EditText mEditSerch;
    @BindView(R.id.xbanner)
    XBanner mXBanner;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.scrollView_serch)
    ScrollView mScrollSerch;
    @BindView(R.id.icon_rexiao)
    ImageView mIconRexiao;
    @BindView(R.id.recy_rexiao)
    RecyclerView mRecyRexiao;
    @BindView(R.id.icon_moli)
    ImageView mIconMoli;
    @BindView(R.id.recy_moli)
    RecyclerView mRecyMoli;
    @BindView(R.id.icon_pinzhi)
    ImageView mIconPinzhi;
    @BindView(R.id.recy_pinzhi)
    RecyclerView mRecyPinzhi;
    @BindView(R.id.recy_serch)
    XRecyclerView mRecySerch;
    @BindView(R.id.serch_layout)
    RelativeLayout mSerchLayout;
    private Unbinder unbinder;
    private List<HomeBannerBean.Result> list = new ArrayList<>();
    private List<HomeBannerBean.Result> bannerList = new ArrayList<>();
    private List<String> mImgesUrl ;
    private HomePresenterImp homePresenterImp;
    private SerchPresenter serchPresenter;
    private List<HomeListBean.ResultBean.MlssBean> mlssBean;
    private List<HomeListBean.ResultBean.RxxpBean> rxxpBean;
    private List<HomeListBean.ResultBean.PzshBean> pzshBean;
    private List<SearchBean.Result> arrayList = new ArrayList<>();
    private SerchAdapter serchAdapter;
    int page = 1;
    private String editSerch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBanner();
        homePresenterImp = new HomePresenterImp(this);
        homePresenterImp.showList();
    }

    private void onBanner() {
        /*new OkHttpUtil().OkHttpGet(Apis.BANNER_URL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                HomeBannerBean bannerBean = gson.fromJson(data, HomeBannerBean.class);
                list = bannerBean.getResult();
                if (list != null) {
                    bannerList.addAll(list);
                }
            }

            @Override
            public void OkHttpError(String error) {

            }
        });*/
        mImgesUrl = new ArrayList<>();
        /*if (bannerList!=null) {
            for (int i = 0; i < bannerList.size(); i++) {
                mImgesUrl.add(bannerList.get(i).getImageUrl());
            }
        }*/
        mImgesUrl.add("http://172.17.8.100/images/small/banner/cj.png");
        mImgesUrl.add("http://172.17.8.100/images/small/banner/hzp.png");
        mImgesUrl.add("http://172.17.8.100/images/small/banner/lyq.png");
        mImgesUrl.add("http://172.17.8.100/images/small/banner/px.png");
        mXBanner.setData(mImgesUrl, null);
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(mImgesUrl.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效，选择一个即可，总的大概就这么多效果啦，欢迎使用
        mXBanner.setPageTransformer(Transformer.Default);//横向移动
        mXBanner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显
        mXBanner.setPageTransformer(Transformer.ZoomFade); // 缩小本页，同时放大另一页
        mXBanner.setPageTransformer(Transformer.ZoomCenter); //本页缩小一点，另一页就放大
        mXBanner.setPageTransformer(Transformer.ZoomStack); // 本页和下页同事缩小和放大
        mXBanner.setPageTransformer(Transformer.Stack);  //本页和下页同时左移
        mXBanner.setPageTransformer(Transformer.Depth);  //本页左移，下页从后面出来
        mXBanner.setPageTransformer(Transformer.Zoom);  //本页刚左移，下页就在后面
        // 设置XBanner页面切换的时间，即动画时长
        mXBanner.setPageChangeDuration(2000);
    }

    @OnClick({R.id.Im_menu, R.id.Im_serch, R.id.text_serch, R.id.icon_rexiao, R.id.recy_rexiao, R.id.icon_moli, R.id.recy_moli, R.id.icon_pinzhi, R.id.recy_pinzhi})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.Im_menu:
                onShowPopwindow();
                break;
            case R.id.text_serch:
                onSerch();
                break;
            case R.id.Im_serch:
                onSerchShow();
                break;
            case R.id.icon_rexiao:

                break;
            case R.id.icon_moli:
                break;
            case R.id.icon_pinzhi:
                break;
        }
    }

    private void onShowPopwindow() {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popwindow, null, false);
        PopupWindow popupWindow = new PopupWindow(popView, 1100, 500, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.showAsDropDown(mImMenu, 0, 0);
    }

    private void onSerch() {
        editSerch = mEditSerch.getText().toString();
        if (TextUtils.isEmpty(editSerch)) {
            mTextSerch.setVisibility(View.GONE);
            mEditSerch.setVisibility(View.GONE);
            mImSerch.setVisibility(View.VISIBLE);
        }else {
            mScrollView.setVisibility(View.GONE);
            mScrollSerch.setVisibility(View.VISIBLE);
            serchPresenter = new SerchPresenter(this);
            mRecySerch.setVisibility(View.VISIBLE);
            mSerchLayout.setVisibility(View.GONE);
            serchPresenter.serch(editSerch,page,10);
            mRecySerch.setLoadingMoreEnabled(true);
            mRecySerch.setPullRefreshEnabled(true);
            mRecySerch.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page = 1;
                    arrayList.clear();
                    serchPresenter.serch(editSerch,page,10);
                    mRecySerch.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    serchPresenter.serch(editSerch,page,10);
                    serchAdapter.notifyDataSetChanged();
                    mRecySerch.loadMoreComplete();
                    if (page>2) {
                        ToastUtils.showToast(getActivity(),"没有更多数据了");
                    }
                }

            });
        }

    }

    private void onSerchShow() {
        mImSerch.setVisibility(View.GONE);
        mTextSerch.setVisibility(View.VISIBLE);
        mEditSerch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        homePresenterImp.deach();
        serchPresenter.deach();
    }

    @Override
    public void onSuccessList(HomeListBean.ResultBean mList) {
        mlssBean = mList.getMlss();
        rxxpBean = mList.getRxxp();
        pzshBean = mList.getPzsh();

        HomeMoliAdapter homeMoliAdapter = new HomeMoliAdapter(getActivity(), mlssBean);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager .setOrientation( LinearLayoutManager.VERTICAL );
        mRecyMoli.setLayoutManager(manager);
        mRecyMoli.setAdapter(homeMoliAdapter);
        homeMoliAdapter.setOnClick(new HomeMoliAdapter.OnClick() {
            @Override
            public void onClickListener(int position) {
                ToastUtils.showToast(getActivity(),position+"");
            }
        });

        HomeRexiaoAdapter homeRexiaoAdapter = new HomeRexiaoAdapter(getActivity(), rxxpBean);
        LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
        linearManager .setOrientation( LinearLayoutManager.HORIZONTAL );
        mRecyRexiao.setLayoutManager(linearManager);
        mRecyRexiao.setAdapter(homeRexiaoAdapter);
        homeRexiaoAdapter.setOnClick(new HomeRexiaoAdapter.OnClick() {
            @Override
            public void onClickListener(int position) {
                ToastUtils.showToast(getActivity(),position+"");
            }
        });

        HomePinzhiAdapter homePinzhiAdapter = new HomePinzhiAdapter(getActivity(), pzshBean);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyPinzhi.setLayoutManager(gridLayoutManager);
        mRecyPinzhi.setAdapter(homePinzhiAdapter);
        homePinzhiAdapter.setOnClick(new HomePinzhiAdapter.OnClick() {
            @Override
            public void onClickListener(int position) {
                ToastUtils.showToast(getActivity(),position+"");
            }
        });
    }

    @Override
    public void onSuccess(List<SearchBean.Result> mList) {
        arrayList.addAll(mList);
        if (arrayList.size()>0) {
            mRecySerch.setVisibility(View.VISIBLE);
            mSerchLayout.setVisibility(View.GONE);
            serchAdapter = new SerchAdapter(getContext(), arrayList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            mRecySerch.setLayoutManager(gridLayoutManager);
            mRecySerch.setAdapter(serchAdapter);
            serchAdapter.notifyDataSetChanged();
            serchAdapter.setOnClick(new SerchAdapter.OnClick() {
                @Override
                public void onClickListener(int position) {
                    ToastUtils.showToast(getActivity(),position+"");

                }
            });
        }else {
            mRecySerch.setVisibility(View.GONE);
            mSerchLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFalierd(String msg) {

    }

    @Override
    public void onFalier(String msg) {
        ToastUtils.showToast(getActivity(),msg.toString());
        Log.i("魔力时尚", "onFalier: "+msg);
    }
}

