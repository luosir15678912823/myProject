package com.qianfeng.mall.fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianfeng.mall.QueryActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.adapter.GroupBuyAdapter;
import com.qianfeng.mall.broadcastReceiver.NetWorkReceiver;
import com.qianfeng.mall.modle.GroupBuyToday;
import com.qianfeng.mall.util.HttpUtil;
import com.qianfeng.mall.util.JsonUtil;
import com.qianfeng.mall.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-18.
 */
public class GroupBuyFragment extends Fragment implements NetWorkReceiver.NetWorkListener {
    private static final int GET_GROUP_BUY_TODAY_DATA = 100;
    private static final String TAG = "GroupBuyFragment";
    private View view;
    EditText etSearch;
    TextView tvSearch;
    RecyclerView rvGroupBuy;
    private GroupBuyAdapter adapter;
    private GroupBuyToday groupBuyToday;
    List<GroupBuyToday.ShowapiResBodyBean.ListBean> list = new ArrayList<>();;
    private GridLayoutManager layoutManager;
    private ProgressBar pb;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case GET_GROUP_BUY_TODAY_DATA:

                    String json = (String) msg.obj;
                    groupBuyToday = JsonUtil.parseGroupBuyToday(json);
                    if (groupBuyToday == null) {
                        return;
                    }
                    Log.e(TAG, "handleMessage: groupBuyToday=" + groupBuyToday.getShowapi_res_body().getList());
                    if (list.size()>=0){
                        list.clear();
                    }
                    list.addAll(groupBuyToday.getShowapi_res_body().getList());
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private NetWorkReceiver netWorkReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netWorkReceiver = new NetWorkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetWorkReceiver.INTENT_ATION);
        getContext().registerReceiver(netWorkReceiver, intentFilter);
        netWorkReceiver.setListener(this);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_group_buy, container, false);
            rvGroupBuy = ((RecyclerView) view.findViewById(R.id.rvGroupBuy));
            etSearch = ((EditText) ((LinearLayout) view.findViewById(R.id.incl)).findViewById(R.id.etSearch));
            tvSearch = ((TextView) ((LinearLayout) view.findViewById(R.id.incl)).findViewById(R.id.tvSearch));
            pb = ((ProgressBar) view.findViewById(R.id.pb));
        }

        adapter = new GroupBuyAdapter(getActivity(), list);
        layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        rvGroupBuy.setLayoutManager(layoutManager);
        rvGroupBuy.setAdapter(adapter);

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = etSearch.getText().toString().trim();
                Intent intent = new Intent(getContext(), QueryActivity.class);
                intent.putExtra("keyWord", keyWord);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
        ThreadUtil.doHttpByExecutePool(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getStringByGroupBuyToday();
                Message message = Message.obtain();
                message.obj = json;
                message.what = GET_GROUP_BUY_TODAY_DATA;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void networkIsAvailable() {
        initData();
    }
}
