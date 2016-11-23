package com.qianfeng.mall.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.Comment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-09.
 */

public class CommentAdapter extends BaseAdapter {

    private  LayoutInflater inflater;
    Context context;
    List<Comment> data;

    public CommentAdapter(Context context, List<Comment> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_comment, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }

        Comment comment = data.get(position);
        holder.tvUserName.setText(comment.getNickName());
        holder.tvContent.setText(comment.getCommentContent());
        holder.tvCommentTime.setText(comment.getCreatedAt());
        String url = comment.getPicUrl();
        if (url==null||url.length()<=0) {
            holder.ivPic.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.person_big));
        }else{
            holder.ivPic.setImageURI(Uri.parse(url));
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.ivPic)
        SimpleDraweeView ivPic;
        @Bind(R.id.tvUserName)
        TextView tvUserName;
        @Bind(R.id.tvContent)
        TextView tvContent;
        @Bind(R.id.tvCommentTime)
        TextView tvCommentTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
