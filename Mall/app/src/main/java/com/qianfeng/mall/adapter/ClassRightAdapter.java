package com.qianfeng.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qianfeng.mall.DetailActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.QueryBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qf on 2016/10/21.
 */
public class ClassRightAdapter extends RecyclerView.Adapter<ClassRightAdapter.ViewHolder> {

    private static final String TAG = "test";
    private final LayoutInflater inflater;
    Context context;
    private View view;
    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list;

    public ClassRightAdapter(Context context, List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean contentlistBean = list.get(position);
        final String name = contentlistBean.getName();
        final String imgUrl = contentlistBean.getImg();


        Glide.with(context)
                .load(Uri.parse(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivClassPic);
        holder.tvName.setText(name);

        holder.ivClassPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",contentlistBean);
                bundle.putSerializable("flag", DetailActivity.SIMPLE_FLAG);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size()<0){
            return 30;
        }else {
            return list.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivClassPic)
        ImageView ivClassPic;
        @Bind(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
