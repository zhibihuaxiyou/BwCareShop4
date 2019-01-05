package com.bwie.bwcareshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.bean.HomeListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * author：张腾
 * date：2018/12/30
 */

public class HomeMoliAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<HomeListBean.ResultBean.MlssBean> mlssBeanList = new ArrayList<>();

    public HomeMoliAdapter(Context mcontext, List<HomeListBean.ResultBean.MlssBean> mlssBeanList) {
        this.mcontext = mcontext;
        this.mlssBeanList = mlssBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.magic_item_child, viewGroup, false);
        return new MoLiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof MoLiViewHolder) {
            Picasso.with(mcontext).load(mlssBeanList.get(0).getCommodityList().get(i).getMasterPic()).into(((MoLiViewHolder) holder).moli_image);
            ((MoLiViewHolder) holder).moli_name.setText(mlssBeanList.get(0).getCommodityList().get(i).getCommodityName());
            ((MoLiViewHolder) holder).moli_price.setText("￥:" + mlssBeanList.get(0).getCommodityList().get(i).getPrice());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.onClickListener(mlssBeanList.get(0).getCommodityList().get(i).getCommodityId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mlssBeanList.get(0).getCommodityList().size() == 0 ? 0 : mlssBeanList.get(0).getCommodityList().size();
    }

    class MoLiViewHolder extends RecyclerView.ViewHolder {

        private final ImageView moli_image;
        private final TextView moli_name;
        private final TextView moli_price;

        public MoLiViewHolder(@NonNull View itemView) {
            super(itemView);
            moli_image = itemView.findViewById(R.id.moli_image);
            moli_name = itemView.findViewById(R.id.moli_name);
            moli_price = itemView.findViewById(R.id.moli_price);
        }
    }

    public OnClick mOnClick;

    public void setOnClick(OnClick mOnClick){
        this.mOnClick = mOnClick;
    }

    public interface OnClick{
        void onClickListener(int position);
    }
}
