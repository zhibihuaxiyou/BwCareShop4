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
import com.bwie.bwcareshop.bean.CircleBean;
import com.bwie.bwcareshop.utils.TimeUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author：张腾
 * date：2019/1/4
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder>{

    private List<CircleBean.Result> list;
    private Context context;

    public CircleAdapter(List<CircleBean.Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_circle_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String createTime = list.get(i).getCreateTime();
        long stringToDate = TimeUtils.getStringToDate(createTime);
        String dateToString = TimeUtils.getDateToString(stringToDate);
        viewHolder.mHeadPic.setImageURI(list.get(i).getHeadPic());
        viewHolder.mCircleImage.setImageURI(list.get(i).getImage());
        viewHolder.mNickName.setText(list.get(i).getNickName());
        viewHolder.mCreateTime.setText(dateToString);
        viewHolder.mContent.setText(list.get(i).getContent());
        viewHolder.mWhetherGreat.setText(list.get(i).getWhetherGreat()+"");
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mHeadPic,mCircleImage;
        TextView mNickName,mCreateTime,mContent,mWhetherGreat;
        ImageView mIvCircleClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeadPic = itemView.findViewById(R.id.circle_headPic);
            mCircleImage = itemView.findViewById(R.id.circle_review);
            mNickName = itemView.findViewById(R.id.circle_nickName);
            mCreateTime = itemView.findViewById(R.id.circle_createTime);
            mContent = itemView.findViewById(R.id.circle_content);
            mWhetherGreat = itemView.findViewById(R.id.circle_whetherGreat);
            mIvCircleClick = itemView.findViewById(R.id.iv_circle_click);
        }
    }
}
