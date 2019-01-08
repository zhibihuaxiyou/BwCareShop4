package com.bwie.bwcareshop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.adapter.CircleAdapter;
import com.bwie.bwcareshop.bean.CircleBean;
import com.bwie.bwcareshop.mvp.presenter.CirclePresenter;
import com.bwie.bwcareshop.mvp.view.CircleView;
import com.bwie.bwcareshop.mvp.view.MyView;
import com.bwie.bwcareshop.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment implements CircleView {

    @BindView(R.id.recy_circle)
    RecyclerView mRecyCircle;
    private Unbinder unbinder;
    private CirclePresenter circlePresenter;
    private CircleAdapter circleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        circlePresenter = new CirclePresenter(this);
        circlePresenter.showCircle(1,50);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSuccess(List<CircleBean.Result> data) {
        circleAdapter = new CircleAdapter(data, getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyCircle.setLayoutManager(manager);
        mRecyCircle.setAdapter(circleAdapter);
        circleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailer(String msg) {
        ToastUtils.showToast(getActivity(),msg);
    }
}
