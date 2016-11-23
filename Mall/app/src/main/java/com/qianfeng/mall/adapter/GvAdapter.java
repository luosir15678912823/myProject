package com.qianfeng.mall.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.QueryBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qf on 2016/10/31.
 */
public class GvAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    Context context;
    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> querys;

    public GvAdapter(Context context, List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> querys) {
        this.context = context;
        this.querys = querys;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return querys.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean bean = querys.get(position);
        String name = bean.getName();
        String img = bean.getImg();

        holder.tvName.setText(name);
        Glide.with(context).load(Uri.parse(img)).into(holder.ivPic);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.ivPic)
        ImageView ivPic;
        @Bind(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
