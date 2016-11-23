package com.nightly.lovetravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.AttractionsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nightly on 2016/10/18.
 */

public class TourListAdapter extends RecyclerView.Adapter<TourListAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;

    public TourListAdapter(Context context, List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_result_item_linear, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        if (data != null) {
            AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean = data.get(position);
            holder.tvName.setText(bean.getName());
            holder.tvSummary.setText(bean.getSummary().length() > 35 ? bean.getSummary().subSequence(0, 30) + "……" : bean.getSummary());
//            holder.tvAddress.setText(bean.getProName() + bean.getCityName() + bean.getAreaName() + bean.getAddress());
            holder.tvAddress.setText(bean.getAddress().contains(bean.getProName().substring(2))?bean.getAddress():bean.getProName() + bean.getCityName() + bean.getAreaName() + bean.getAddress());
            List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.PicListBean> picList = bean.getPicList();
            int size = picList.size();
            if(size>0){
                Picasso.with(context).load(picList.get(0).getPicUrlSmall())
                        .error(R.mipmap.exit)
                        .into(holder.ivPic);
            }
            holder.tvTotalPic.setText("共"+(size > 1 ? size / 2 : size) + "张图片");
            holder.llMenu.setVisibility(View.GONE);
            holder.llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.llMenu.isShown()){
                        holder.llMenu.setVisibility(View.GONE);
                    }else {
                        holder.llMenu.setVisibility(View.VISIBLE);
                    }
//                    if (listener != null) {
//                        listener.OnItemClick(position);
//                    }
                }
            });
            holder.tvHotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.OnTvHotelClick(position);
                    }
                }
            });
            holder.tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.OnTvDetailClick(position);
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.tvTotalPic)
        TextView tvTotalPic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvSummary)
        TextView tvSummary;
        @BindView(R.id.llRoot)
        LinearLayout llRoot;
        @BindView(R.id.llMenu)
        LinearLayout llMenu;
        @BindView(R.id.tvHotel)
        TextView tvHotel;
        @BindView(R.id.tvDetail)
        TextView tvDetail;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnTvHotelClick(int position);
        void OnTvWeatherClick(int position);
        void OnTvDetailClick(int position);
    }
}
