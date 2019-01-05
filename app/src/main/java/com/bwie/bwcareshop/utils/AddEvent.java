package com.bwie.bwcareshop.utils;

import android.support.v4.app.Fragment;



public class AddEvent {
    private Fragment needAdd;

    public AddEvent(Fragment needAdd) {
        this.needAdd = needAdd;
    }

    public Fragment getNeedAdd() {
        return needAdd;
    }
}
