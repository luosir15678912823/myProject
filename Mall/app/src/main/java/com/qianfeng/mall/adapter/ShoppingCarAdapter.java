package com.qianfeng.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qianfeng.mall.CommentActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.BmobCarBean;
import com.qianfeng.mall.modle.PriseUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016-11-08.
 */

public class ShoppingCarAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    Context context;
    List<BmobCarBean> data;
    private int currentPosition=-1;
    private int count;

    public ShoppingCarAdapter(Context context, List<BmobCarBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shoppingcar, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final BmobCarBean carBean = data.get(position);
        Glide.with(context).load(carBean.getImgUrl()).into(holder.ivPic);
        holder.tvTitle.setText(carBean.getName());

        //移除购物车
        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobCarBean bmobCarBean = new BmobCarBean();
                bmobCarBean.setObjectId(carBean.getObjectId());
                bmobCarBean.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(context, "移除成功", Toast.LENGTH_SHORT).show();
                            data.remove(carBean);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "移除失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("commodityName",carBean.getName());
                context.startActivity(intent);
            }
        });

        final String userId = BmobUser.getCurrentUser().getObjectId();
        holder.llPrise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
                PriseUser priseUser = new PriseUser(userId, carBean.getName());
                priseUser.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {

                        if (e == null) {
                            currentPosition = position;
                            int currentCount = Integer.parseInt(holder.tvPrise.getText().toString().trim());
                            count = currentCount + 1;
                            notifyDataSetChanged();
                        }
                    }
                });

            }
        });

        if (position == currentPosition) {
            holder.tvPrise.setText(count + "");
            holder.ivPrise.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.favorite));
        }else{
            holder.tvPrise.setText(0+"");
            holder.ivPrise.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.favorite_unchecked));
        }

        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.ivPic)
        ImageView ivPic;
        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvPrise)
        TextView tvPrise;
        @Bind(R.id.llPrise)
        LinearLayout llPrise;
        @Bind(R.id.tvComment)
        TextView tvComment;
        @Bind(R.id.tvRemove)
        TextView tvRemove;
        @Bind(R.id.ivPrise)
        ImageView ivPrise;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
