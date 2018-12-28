package com.bwie.bwcareshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.api.Constants;
import com.bwie.bwcareshop.bean.LoginOrRegistBean;
import com.bwie.bwcareshop.mvp.presenter.LoginOrRegistPresenter;
import com.bwie.bwcareshop.mvp.view.LoginOrRegistView;
import com.bwie.bwcareshop.utils.IntentUtils;
import com.bwie.bwcareshop.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginOrRegistView {

    @BindViews({R.id.edit_login_mobile, R.id.edit_login_password})
    public List<EditText> editTexts;
    @BindView(R.id.icon_login_eye)
    public ImageButton mIconEye;
    @BindView(R.id.text_regist)
    public TextView mTextRegist;
    @BindView(R.id.btn_login)
    public Button mBtnLogin;
    @BindView(R.id.login_checkbox)
    CheckBox mLoginCheckbox;
    private LoginOrRegistPresenter loginPresenter;
    private HashMap<String, String> mMap;
    private String login_pwd;
    private String login_phone;
    private SharedPreferences sp;
    private boolean isChecked;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginOrRegistPresenter(this);
        Intent intent = getIntent();
        String regist_phone = intent.getStringExtra("regist_phone");
        String regist_pwd = intent.getStringExtra("regist_pwd");
        editTexts.get(0).setText(regist_phone);
        editTexts.get(1).setText(regist_pwd);
        mMap = new HashMap<>();
        sp = getSharedPreferences("config", MODE_PRIVATE);
        isChecked = sp.getBoolean("isChecked", false);
        if (isChecked) {
            String sp_phone = sp.getString("login_phone", "");
            String sp_pwd = sp.getString("login_pwd", "");
            editTexts.get(0).setText(sp_phone);
            editTexts.get(1).setText(sp_pwd);
            mLoginCheckbox.setChecked(true);
        }
        mIconEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    editTexts.get(1).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else if (event.getAction()== MotionEvent.ACTION_UP){
                    editTexts.get(1).setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return false;
            }
        });
    }


    @OnClick({ R.id.text_regist, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.text_regist:
                IntentUtils.getInstence().intent(this, RegistActivity.class);
                finish();
                break;
            case R.id.btn_login:
                login_phone = editTexts.get(0).getText().toString().trim();
                login_pwd = editTexts.get(1).getText().toString().trim();
                mMap.put(Constants.POST_BODY_KEY_PHONE, login_phone);
                mMap.put(Constants.POST_BODY_KEY_PWD, login_pwd);
                loginPresenter.loginOrRegist(Apis.LOGIN_URL, mMap, LoginOrRegistBean.class);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        LoginOrRegistBean loginBean = (LoginOrRegistBean) data;
        if (loginBean.getStatus().equals("0000")) {
            ToastUtils.showToast(this, loginBean.getMessage());
            editor = sp.edit();
            if (mLoginCheckbox.isChecked()) {
                editor.putBoolean("isChecked", true);
                editor.putString("login_phone", login_phone);
                editor.putString("login_pwd", login_pwd);
            }else {
                editor.putBoolean("isChecked", false);
            }
            editor.apply();
            Bundle bundle = new Bundle();
            String headPic = loginBean.getResult().getHeadPic();
            String nickName = loginBean.getResult().getNickName();
            String phone = loginBean.getResult().getPhone();
            String sessionId = loginBean.getResult().getSessionId();
            int sex = loginBean.getResult().getSex();
            int userId = loginBean.getResult().getUserId();
            bundle.putString("headPic",headPic);
            bundle.putString("nickName",nickName);
            bundle.putString("phone",phone);
            bundle.putString("sessionId",sessionId);
            bundle.putInt("sex",sex);
            bundle.putInt("userId",userId);
            IntentUtils.getInstence().intent(this, MainActivity.class,bundle);
            finish();
        } else {
            ToastUtils.showToast(this, loginBean.getMessage());
        }
    }

    @Override
    public void onFailer(String msg) {
        ToastUtils.showToast(this, msg);
    }
}
