package com.bwie.bwcareshop.netWork;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bwie.bwcareshop.app.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * author：张腾
 * date：2019/1/4
 */
public class CustomIntercept implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        SharedPreferences sp = App.mcontext.getSharedPreferences("config", Context.MODE_PRIVATE);
        String userId = sp.getString("userId", "");
        String sessionId = sp.getString("sessionId", "");

        Request.Builder newBuilder = original.newBuilder();
        newBuilder.method(original.method(), original.body());
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(sessionId)) {
            newBuilder.addHeader("userId", userId);
            newBuilder.addHeader("sessionId", sessionId);
        }
        Request request = newBuilder.build();
        return chain.proceed(request);
    }
}

