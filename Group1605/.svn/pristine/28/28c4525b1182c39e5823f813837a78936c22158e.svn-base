package com.nightly.lovetravel.adapter;

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
    private boolean isListSizeSingle = false;

    public BannerAdapter(List<ImageView> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        //取超大的数，实现无线循环效果
        if (mList.size() <= 1) {
            return mList.size();
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mList.size() <= 1) {
            container.removeView(mList.get(position));
            container.addView(mList.get(position));
            return mList.get(position);
        } else {
            container.removeView(mList.get(position % mList.size()));
            container.addView(mList.get(position % mList.size()));
            return mList.get(position % mList.size());
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mList.size() <= 1) {
            container.removeView(mList.get(position));
        } else {
            container.removeView(mList.get(position % mList.size()));
        }
    }

}
