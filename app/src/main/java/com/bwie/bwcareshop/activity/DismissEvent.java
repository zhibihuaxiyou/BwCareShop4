package com.bwie.bwcareshop.activity;

import android.support.v4.app.Fragment;

/**
 * author：张腾
 * date：2019/1/4
 */
public class DismissEvent {
    private Fragment needDismiss;

    public DismissEvent(Fragment needDismiss) {
        this.needDismiss = needDismiss;
    }

    public Fragment getNeedDismiss() {
        return needDismiss;
    }
}
