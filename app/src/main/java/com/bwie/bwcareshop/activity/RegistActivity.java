package com.bwie.bwcareshop.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.api.Constants;
import com.bwie.bwcareshop.bean.LoginOrRegistBean;
import com.bwie.bwcareshop.mvp.presenter.PresenterImp;
import com.bwie.bwcareshop.mvp.view.MyView;
import com.bwie.bwcareshop.utils.IntentUtils;
import com.bwie.bwcareshop.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.edit_regist_mobile)
    EditText mEditRegistMobile;
    @BindView(R.id.edit_verification_code)
    EditText mEditVerificationCode;
    @BindView(R.id.text_verification_code)
    TextView mTextVerificationCode;
    @BindView(R.id.edit_regist_password)
    EditText mEditRegistPassword;
    @BindView(R.id.icon_eye)
    ImageButton mIconEye;
    @BindView(R.id.text_regist_to_login)
    TextView mTextRegistToLogin;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;
    private PresenterImp registPresenter;
    private HashMap<String, String> map;
    private String regist_phone;
    private String regist_pwd;
    int i = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        registPresenter = new PresenterImp(this);
        mIconEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mEditRegistPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mEditRegistPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return false;
            }
        });

    }

    @OnClick({R.id.text_verification_code, R.id.text_regist_to_login, R.id.btn_regist})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.text_verification_code:
                break;
            case R.id.text_regist_to_login:
                IntentUtils.getInstence().intent(this, LoginActivity.class);
                finish();
                break;
            case R.id.btn_regist:
                map = new HashMap<>();
                regist_pwd = mEditRegistPassword.getText().toString().trim();
                map.put(Constants.POST_BODY_KEY_PHONE, regist_phone);
                map.put(Constants.POST_BODY_KEY_PWD, regist_pwd);
                registPresenter.loginOrRegist(Apis.REGIST_URL, map, LoginOrRegistBean.class);
                break;
        }
    }



    @Override
    public void onSuccess(Object data) {
        LoginOrRegistBean registBean = (LoginOrRegistBean) data;
        if (registBean.getStatus().equals("0000")) {
            ToastUtils.showToast(this, registBean.getMessage());
            Bundle bundle = new Bundle();
            bundle.putString("regist_phone", regist_phone);
            bundle.putString("regist_pwd", regist_pwd);
            IntentUtils.getInstence().intent(this, LoginActivity.class, bundle);
            finish();
        } else {
            ToastUtils.showToast(this, registBean.getMessage());
        }
    }

    @Override
    public void onFailer(String msg) {
        ToastUtils.showToast(this, msg);
    }


    protected void onDestroy() {
        super.onDestroy();
    }


}
