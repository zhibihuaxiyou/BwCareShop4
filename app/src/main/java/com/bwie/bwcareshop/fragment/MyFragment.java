package com.bwie.bwcareshop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.activity.AddressActivity;
import com.bwie.bwcareshop.activity.CircleActivity;
import com.bwie.bwcareshop.activity.FootActivity;
import com.bwie.bwcareshop.activity.InformationActivity;
import com.bwie.bwcareshop.activity.WalletActivity;
import com.bwie.bwcareshop.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends Fragment {

    @BindView(R.id.edit_information)
    TextView mEditInformation;
    @BindView(R.id.edit_circle)
    TextView mEditCircle;
    @BindView(R.id.edit_foot)
    TextView mEditFoot;
    @BindView(R.id.edit_wallet)
    TextView mEditWallet;
    @BindView(R.id.edit_address)
    TextView mEditAddress;
    private Unbinder unbinder;
    @BindView(R.id.icon_bitmap_my_copy)
    public ImageView mIconBitmapMyCopy;
    private View view;
    private Bundle bundle;

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
        Intent intent = getActivity().getIntent();
        String headPic = intent.getStringExtra("headPic");
        Glide.with(getActivity()).load(headPic).into(mIconBitmapMyCopy);
    }

    @OnClick({R.id.edit_information, R.id.edit_circle, R.id.edit_foot, R.id.edit_wallet, R.id.edit_address})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.edit_information:
                IntentUtils.getInstence().intent(getActivity(), InformationActivity.class);
                break;
            case R.id.edit_circle:
                IntentUtils.getInstence().intent(getActivity(), CircleActivity.class);
                break;
            case R.id.edit_foot:
                IntentUtils.getInstence().intent(getActivity(), FootActivity.class);
                break;
            case R.id.edit_wallet:
                IntentUtils.getInstence().intent(getActivity(), WalletActivity.class);
                break;
            case R.id.edit_address:
                IntentUtils.getInstence().intent(getActivity(), AddressActivity.class);
                break;
        }
    }
}
