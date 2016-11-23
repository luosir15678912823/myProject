package com.qianfeng.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qianfeng.mall.DetailActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.GroupBuyToday;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-18.
 */
public class GroupBuyAdapter extends RecyclerView.Adapter<GroupBuyAdapter.Holder> {

    private static final String TAG = "GroupBuyAdapter";
    Context context;
    String summary="";
    List<GroupBuyToday.ShowapiResBodyBean.ListBean> list;
    private View view;

    public GroupBuyAdapter(Context context, List<GroupBuyToday.ShowapiResBodyBean.ListBean> list) {
        this.context = context;
        this.list = list;
        Log.e(TAG, "GroupBuyAdapter: ");
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_group_buy, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (list == null || list.size() <= 0) {
            return;
        }
        final GroupBuyToday.ShowapiResBodyBean.ListBean bean = list.get(position);
        final String imgUrl = bean.getImg();
        String end_time = bean.getEnd_time();
        String start_time = bean.getStart_time();
        String title = bean.getTitle();
        int market_price = bean.getMarket_price();
        String price = bean.getPrice();
        String show_count = bean.getShow_count();
        final String url = bean.getUrl();

        holder.tvTitle.setText(title);
        holder.tvCurrentPrice.setText("￥" + price);
        holder.tvMarketPrice.setText("￥" + market_price);
        holder.tvShowCount.setText("仅剩" + show_count + "件");

//        summary=title+"\n"+"现价：￥"+price+"\n"+"市场价：￥"+market_price+"\n"+"仅剩" + show_count + "件"+"\n"+"活动时间："+start_time+"~"+end_time+"\n";

        Glide.with(context)
                .load(Uri.parse(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",bean);
                bundle.putInt("flag",DetailActivity.COMPLEX_FLAG);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() <= 0) {
            return 20;
        } else {
            return list.size();
        }
    }


    class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvCurrentPrice)
        TextView tvCurrentPrice;
        @Bind(R.id.tvMarketPrice)
        TextView tvMarketPrice;
        @Bind(R.id.tvShowCount)
        TextView tvShowCount;

        Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
