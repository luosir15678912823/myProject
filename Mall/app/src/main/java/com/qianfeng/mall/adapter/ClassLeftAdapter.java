package com.qianfeng.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.mall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qf on 2016/10/21.
 */
public class ClassLeftAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    Context context;
    String[] data;
    int currentPosition=0;

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
    public ClassLeftAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.item_classname, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{

            viewHolder=((ViewHolder) convertView.getTag());
        }
        viewHolder.tvClassName.setText(data[position]);
        if (position!=currentPosition){
            convertView.setBackgroundResource(R.drawable.shape_class_left_normal);
        }else {
            convertView.setBackgroundResource(R.drawable.shape_class_left_selected);
        }

        return convertView;
    }

     class ViewHolder {
        @Bind(R.id.tvClassName)
        TextView tvClassName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
