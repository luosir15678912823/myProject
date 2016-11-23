package com.qianfeng.mall.fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianfeng.mall.DetailActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.WebViewActivity;
import com.qianfeng.mall.adapter.BannerAdapter;
import com.qianfeng.mall.adapter.GvAdapter;
import com.qianfeng.mall.broadcastReceiver.NetWorkReceiver;
import com.qianfeng.mall.modle.Advertisment;
import com.qianfeng.mall.modle.QueryBean;
import com.qianfeng.mall.util.HttpUtil;
import com.qianfeng.mall.util.JsonUtil;
import com.qianfeng.mall.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-18.
 */
public class HomeFragment extends Fragment implements NetWorkReceiver.NetWorkListener {
    private static final int GOT_DATA_ADR = 10;
    private static final int GOT_DATA_QUERY = 20;
    private static final String TAG = "test";
    ViewPager vp;
    TextView tvAds;
    private View view;
    private int currentAdr;
    GridView gvHome;

    List<Advertisment.ShowapiResBodyBean.NoticeListBean> advertisments = new ArrayList<>();
    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> querys = new ArrayList<>();
    List<ImageView> imageViews = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case GOT_DATA_ADR:
                    String json = (String) msg.obj;
                    Log.e(TAG, "handleMessage: 公告json=" + json);
                    Advertisment advertisment = JsonUtil.parseAdvertisment(json);
                    if (advertisment == null || advertisment.getShowapi_res_body() == null || advertisment.getShowapi_res_body().getNoticeList() == null || advertisment.getShowapi_res_body().getNoticeList().size() <= 0) {

                        return;
                    }
                    advertisments.clear();
                    advertisments.addAll(advertisment.getShowapi_res_body().getNoticeList());
                    switchAdr();
                    break;

                case GOT_DATA_QUERY:
                    String jsonQuery = (String) msg.obj;
                    QueryBean queryBean = JsonUtil.parseQueryBean(jsonQuery);
                    if (queryBean == null || queryBean.getShowapi_res_body() == null || queryBean.getShowapi_res_body().getPageBean()
                            == null || queryBean.getShowapi_res_body().getPageBean().getContentlist() == null || queryBean.getShowapi_res_body().getPageBean().getContentlist().size() <= 0) {
                        return;
                    }
                    querys.clear();
                    querys.addAll(queryBean.getShowapi_res_body().getPageBean().getContentlist());
                    adapter.notifyDataSetChanged();
                    switchAdrPic();
                    break;
            }
        }
    };
    private int temp;
    private GvAdapter adapter;
    private NetWorkReceiver netWorkReceiver;
    private BannerAdapter bannerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netWorkReceiver = new NetWorkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetWorkReceiver.INTENT_ATION);
        getContext().registerReceiver(netWorkReceiver, intentFilter);
        netWorkReceiver.setListener(this);
        initAdrData();
        initQueryData();
        adapter = new GvAdapter(getActivity(), querys);
    }

    private void initQueryData() {
        ThreadUtil.doHttpByExecutePool(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getQueryBean("", 1);
                Message message = handler.obtainMessage();
                message.obj = json;
                message.what = GOT_DATA_QUERY;
                handler.sendMessage(message);
            }
        });
    }

    private void initAdrData() {
        ThreadUtil.doHttpByExecutePool(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getAdvertisment();
                Message message = handler.obtainMessage();
                message.obj = json;
                message.what = GOT_DATA_ADR;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
            tvAds = ((TextView) view.findViewById(R.id.tvAds));
            vp = ((ViewPager) view.findViewById(R.id.vp));
            gvHome = ((GridView) view.findViewById(R.id.gvHome));
            ButterKnife.bind(this, view);
        }

        tvAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = advertisments.get(temp).getLink();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        gvHome.setAdapter(adapter);

        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", querys.get(position));
                bundle.putSerializable("flag", DetailActivity.SIMPLE_FLAG);
                intent.putExtra("bundle", bundle);
                startActivity(intent);

            }
        });
//        switchAdrPic();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void switchAdr() {

        if (tvAds == null || advertisments == null) {
            return;
        }
        temp = currentAdr % advertisments.size();
        String time = advertisments.get(temp).getTime();
        String title = advertisments.get(temp).getTitle();
        tvAds.setText(title);
        currentAdr++;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchAdr();
            }
        }, 5000);
    }

    public void switchAdrPic() {
        for (int i = 0; i < querys.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getActivity()).load(Uri.parse(querys.get(i).getImg())).into(imageView);
            imageViews.add(imageView);
        }
        if (bannerAdapter==null){
            bannerAdapter = new BannerAdapter(imageViews, getContext());
            vp.setAdapter(bannerAdapter);
            autoPalyByHandler();
        }
    }

    private void autoPalyByHandler() {
        if (vp.getCurrentItem() == Integer.MAX_VALUE - 1) {
            vp.setCurrentItem(0);
        } else {
            vp.setCurrentItem(vp.getCurrentItem() + 1);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoPalyByHandler();
            }
        }, 5000);
    }


    @Override
    public void networkIsAvailable() {
        initAdrData();
        initQueryData();
    }
}
