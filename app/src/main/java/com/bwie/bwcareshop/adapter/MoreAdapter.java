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
import com.bwie.bwcareshop.bean.MoreBean;
import com.bwie.bwcareshop.bean.SearchBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/3
 */
public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder>{
    private Context context;
    private List<MoreBean.ResultBean> list;

    public MoreAdapter(Context context, List<MoreBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.serch_item_child, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreAdapter.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof MoreAdapter.ViewHolder) {
            Picasso.with(context).load(list.get(i).getMasterPic()).into(viewHolder.serch_image);
            viewHolder.serch_name.setText(list.get(i).getCommodityName());
            viewHolder.serch_price.setText("￥:" + list.get(i).getPrice());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.onClickListener(list.get(i).getCommodityId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView serch_image;
        TextView serch_name;
        TextView serch_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serch_image = itemView.findViewById(R.id.serch_image);
            serch_name = itemView.findViewById(R.id.serch_name);
            serch_price = itemView.findViewById(R.id.serch_price);
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
