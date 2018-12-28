package com.bwie.bwcareshop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.bwcareshop.activity.LoginActivity;
import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.utils.IntentUtils;
import com.bwie.bwcareshop.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.icon_bitmap_my_copy)
    public ImageView mIconBitmapMyCopy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIconBitmapMyCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getActivity(),"成功");
                IntentUtils.getInstence().intent(getActivity(),LoginActivity.class);
            }
        });
    }
}
