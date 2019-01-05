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

public class HomePinzhiAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<HomeListBean.ResultBean.PzshBean> pzshBeanList;

    public HomePinzhiAdapter(Context mcontext, List<HomeListBean.ResultBean.PzshBean> pzshBeanList) {
        this.mcontext = mcontext;
        this.pzshBeanList = pzshBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.life_item_child, viewGroup, false);
        return new PinZhiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof PinZhiViewHolder) {
            Picasso.with(mcontext).load(pzshBeanList.get(0).getCommodityList().get(i).getMasterPic()).into(((PinZhiViewHolder) holder).pinzhi_image);
            ((PinZhiViewHolder) holder).pinzhi_name.setText(pzshBeanList.get(0).getCommodityList().get(i).getCommodityName());
            ((PinZhiViewHolder) holder).pinzhi_price.setText("￥:" + pzshBeanList.get(0).getCommodityList().get(i).getPrice());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.onClickListener(pzshBeanList.get(0).getCommodityList().get(i).getCommodityId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pzshBeanList.get(0).getCommodityList().size() == 0 ? 0 : pzshBeanList.get(0).getCommodityList().size();
    }

    class PinZhiViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pinzhi_image;
        private final TextView pinzhi_name;
        private final TextView pinzhi_price;

        public PinZhiViewHolder(@NonNull View itemView) {
            super(itemView);
            pinzhi_image = itemView.findViewById(R.id.pinzhi_image);
            pinzhi_name = itemView.findViewById(R.id.pinzhi_name);
            pinzhi_price = itemView.findViewById(R.id.pinzhi_price);
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
