package com.nightly.lovetravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.CityHotelBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<CityHotelBean.ShowapiResBodyBean.ListBean> data;

    public HotelListAdapter(Context context, List<CityHotelBean.ShowapiResBodyBean.ListBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.hotel_detail_list_item, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final CityHotelBean.ShowapiResBodyBean.ListBean listBean = data.get(position);
        holder.tvHotelName.setText(listBean.getHotelName());
        Log.e("test", "hotel: " + listBean.getCommentGood() + "," + listBean.getCommentTotal());
        if (!listBean.getCommentTotal().equals("") && !listBean.getCommentGood().equals("")) {
            holder.tvHotelComment.setText("好评：" + listBean.getCommentGood() + "  评价总数：" + listBean.getCommentTotal());
        } else {
            holder.tvHotelComment.setText("暂无评价");
        }
        holder.tvHotelAddress.setText(listBean.getAddress());
        holder.tvHotelPrice.setText(listBean.getLowestPrice() + "元起");
        Picasso.with(context).load(listBean.getImg()).into(holder.ivPhoto);

        holder.llHotelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.toHotelDetail(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;
        @BindView(R.id.tvHotelName)
        TextView tvHotelName;
        @BindView(R.id.tvHotelComment)
        TextView tvHotelComment;
        @BindView(R.id.tvHotelAddress)
        TextView tvHotelAddress;
        @BindView(R.id.tvHotelPrice)
        TextView tvHotelPrice;
        @BindView(R.id.llHotelList)
        LinearLayout llHotelList;

        Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener{
        void toHotelDetail(int position);
    }
}

