package com.qianfeng.mall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Nightly on 2016/10/31.
 */

public class BannerAdapter extends PagerAdapter {

    //数据源
    private List<ImageView> mList = null;
    Context context;

    public BannerAdapter(List<ImageView> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        //取超大的数，实现无线循环效果
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mList.get(position % mList.size());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position % mList.size()));
    }
}
