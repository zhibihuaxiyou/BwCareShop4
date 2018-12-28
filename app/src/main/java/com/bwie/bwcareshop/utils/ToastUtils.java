package com.bwie.bwcareshop.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * author：张腾
 * date：2018/12/27
 */
public class ToastUtils {
    public static void showToast(Context ctx, int id, String str) {
        if (str == null) {
            return;
        }

        Toast toast = Toast.makeText(ctx, ctx.getString(id) + str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(Context ctx, String errInfo) {
        if (errInfo == null) {
            return;
        }

        Toast toast = Toast.makeText(ctx, errInfo, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
