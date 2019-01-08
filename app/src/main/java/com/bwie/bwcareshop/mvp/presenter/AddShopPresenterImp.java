package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.mvp.callback.MyCallBack;
import com.bwie.bwcareshop.mvp.model.AddShopModelImp;
import com.bwie.bwcareshop.mvp.model.ModelImp;
import com.bwie.bwcareshop.mvp.view.MyAddView;
import com.bwie.bwcareshop.mvp.view.MyView;

import java.util.Map;

/**
 * author：张腾
 * date：2018/12/28
 */
public class AddShopPresenterImp {

    public AddShopModelImp addShopModelImp;
    public MyAddView myView;

    public AddShopPresenterImp(MyAddView myView){
        this.myView = myView;
        addShopModelImp = new AddShopModelImp();
    }

    public void addShop(String url, final Map<String, String> map, Class clazz){
        addShopModelImp.addShop(url, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                myView.onSuccessed(data);
            }

            @Override
            public void onFailer(String msg) {
                myView.onFailered(msg);
            }
        });
    }
}
