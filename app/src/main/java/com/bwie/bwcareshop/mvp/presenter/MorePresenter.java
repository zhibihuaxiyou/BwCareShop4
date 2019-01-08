package com.bwie.bwcareshop.mvp.presenter;

import com.bwie.bwcareshop.bean.MoreBean;
import com.bwie.bwcareshop.bean.SearchBean;
import com.bwie.bwcareshop.mvp.callback.MoreCallBack;
import com.bwie.bwcareshop.mvp.callback.SerchCallBack;
import com.bwie.bwcareshop.mvp.model.MoreModel;
import com.bwie.bwcareshop.mvp.model.SerchModel;
import com.bwie.bwcareshop.mvp.view.MoreView;
import com.bwie.bwcareshop.mvp.view.SerchView;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public class MorePresenter {
    private MoreModel moreModel;
    private MoreView moreView;
    public MorePresenter(MoreView moreView){
        this.moreView = moreView;
        moreModel = new MoreModel();
    }

    public void deach(){
        moreModel=null;
        moreView=null;
    }

    public void showMore(String labelId, int page, int count){
        moreModel.showMore(labelId, page, count, new MoreCallBack() {
            @Override
            public void onSuccessMore(Object mList) {
                moreView.onSuccessMore(mList);
            }

            @Override
            public void onFalierd(String msg) {
                moreView.onFalierd(msg);
            }
        });
    }
}
