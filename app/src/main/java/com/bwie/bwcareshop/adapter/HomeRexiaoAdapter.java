package com.bwie.bwcareshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bwcareshop.R;
import com.bwie.bwcareshop.bean.HomeListBean;
import com.squareup.picasso.Picasso;

import java.util.List;



/**
 * author：张腾
 * date：2018/12/30
 */

public class HomeRexiaoAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<HomeListBean.ResultBean.RxxpBean> rxxpBeanList;

    public HomeRexiaoAdapter(Context mcontext, List<HomeListBean.ResultBean.RxxpBean> rxxpBeanList) {
        this.mcontext = mcontext;
        this.rxxpBeanList = rxxpBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.newshop_item_child, viewGroup, false);
        return new ReXiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof ReXiaoViewHolder) {
            Picasso.with(mcontext).load(rxxpBeanList.get(0).getCommodityList().get(i).getMasterPic()).into(((ReXiaoViewHolder) holder).rexiao_image);
            ((ReXiaoViewHolder) holder).rexiao_name.setText(rxxpBeanList.get(0).getCommodityList().get(i).getCommodityName());
            ((ReXiaoViewHolder) holder).rexiao_price.setText("￥:" + rxxpBeanList.get(0).getCommodityList().get(i).getPrice());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.onClickListener(rxxpBeanList.get(0).getCommodityList().get(i).getCommodityId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rxxpBeanList.get(0).getCommodityList().size() == 0 ? 0 : rxxpBeanList.get(0).getCommodityList().size();
    }

    class ReXiaoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView rexiao_image;
        private final TextView rexiao_name;
        private final TextView rexiao_price;

        public ReXiaoViewHolder(@NonNull View itemView) {
            super(itemView);
            rexiao_image = itemView.findViewById(R.id.rexiao_image);
            rexiao_name = itemView.findViewById(R.id.rexiao_name);
            rexiao_price = itemView.findViewById(R.id.rexiao_price);

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
