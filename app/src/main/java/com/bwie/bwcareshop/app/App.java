package com.bwie.bwcareshop.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author：张腾
 * date：2018/12/29
 */
public class App extends Application{
    public static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
        Fresco.initialize(mcontext);
    }
}
