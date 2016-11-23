package com.nightly.lovetravel.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.AttractionsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nightly on 2016/10/18.
 */

public class TourGridAdapter extends RecyclerView.Adapter<TourGridAdapter.Holder> {
    private Context context;
    private LayoutInflater inflater;

    public TourGridAdapter(Context context, List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_result_item_grid, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean bean = data.get(position);
        holder.tvName.setText(bean.getName());
        int size = bean.getPicList().size();
        holder.cvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.cvRoot)
        CardView cvRoot;

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
        void OnItemClick(int position);
    }
}
