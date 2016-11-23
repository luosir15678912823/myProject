package com.qianfeng.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qianfeng.mall.DetailActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.QueryBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qf on 2016/10/22.
 */
public class QueryAdapter extends BaseAdapter {


    private final LayoutInflater inflater;
    Context context;
    private View view;
    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list;

    public QueryAdapter(Context context, List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_query,parent,false);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{

            holder=((Holder) convertView.getTag());
        }
        final QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean contentlistBean = list.get(position);
        String imgUrl = contentlistBean.getImg();
        String name = contentlistBean.getName();
        String price = contentlistBean.getPrice();
        holder.sdv.setImageURI(Uri.parse(imgUrl));
        holder.tvName.setText(name);
        holder.tvPrice.setText(price);

        holder.sdv.setOnClickListener(new View.OnClickListener() {
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

        return convertView;
    }

    static class Holder  {
        @Bind(R.id.sdv)
        SimpleDraweeView sdv;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvPrice)
        TextView tvPrice;

        Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
