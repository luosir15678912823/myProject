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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianfeng.mall.QueryActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.adapter.ClassLeftAdapter;
import com.qianfeng.mall.adapter.ClassRightAdapter;
import com.qianfeng.mall.broadcastReceiver.NetWorkReceiver;
import com.qianfeng.mall.modle.QueryBean;
import com.qianfeng.mall.util.HttpUtil;
import com.qianfeng.mall.util.JsonUtil;
import com.qianfeng.mall.util.ThreadUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-18.
 */
public class ClassificationFragment extends Fragment implements NetWorkReceiver.NetWorkListener {
    private static final String TAG = "test";
    private final int MSG_GOT_Classiffication_DATA = 10;
    private static final int MSG_GOT_QUERY_BEAN = 20;
    TextView tvQuery;
    EditText etSearch;
    TextView tvSearch;
    ListView lvClass;
    RecyclerView rvClass;
    private View view;
    private ProgressBar pb;

    private String[] data = {"男装", "女装", "童装", "鞋子", "袜子", "背包", "袋子", "露营旅行", "登山",
            "攀岩", "滑雪", "板", "工具", "仪器", "眼镜", "骑行", "自行车", "护具", "头盔"};
    private ClassRightAdapter rvAdapter;
    private Map<String, List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean>> map = new HashMap<>();
    ArrayList<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list = new ArrayList<>();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_GOT_Classiffication_DATA:
                    Bundle bundleClass = (Bundle) msg.obj;
                    String json = bundleClass.getString("json");
                    String name = bundleClass.getString("name");
                    QueryBean bean = JsonUtil.parseQueryBean(json);
                    Log.e(TAG, "handleMessage: 查询到的数据json=" + json);
                    if (bean == null || bean.getShowapi_res_body() == null || bean.getShowapi_res_body().getPageBean() == null) {
                        return;
                    }
                    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> contentlist = bean.getShowapi_res_body().getPageBean().getContentlist();
                    if (contentlist == null || contentlist.size() <= 0) {
                        return;
                    }
                    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> beanList = map.get(name);
                    if (beanList == null || beanList.size() <= 0) {
                        list.clear();
                        list.addAll(contentlist);
                        map.put(name, contentlist);
                        rvAdapter.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };
    private NetWorkReceiver netWorkReceiver;
    private ClassLeftAdapter leftAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        netWorkReceiver = new NetWorkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetWorkReceiver.INTENT_ATION);
        getContext().registerReceiver(netWorkReceiver, intentFilter);
        netWorkReceiver.setListener(this);
        rvAdapter = new ClassRightAdapter(getContext(), list);
        initData(data[0]);
    }

    private void initData(final String keyword) {
        ThreadUtil.doHttpByExecutePool(new Runnable() {
            @Override
            public void run() {

                String json = HttpUtil.getQueryBean(keyword, 1);
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("json", json);
                bundle.putString("name", keyword);
                message.obj = bundle;
                message.what = MSG_GOT_Classiffication_DATA;
                handler.sendMessage(message);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_classification, container, false);
            lvClass = ((ListView) view.findViewById(R.id.lvClass));
            rvClass = ((RecyclerView) view.findViewById(R.id.rvClass));
            pb = ((ProgressBar) view.findViewById(R.id.pb));
            etSearch = ((EditText) ((LinearLayout) view.findViewById(R.id.incl)).findViewById(R.id.etSearch));
            tvSearch = ((TextView) ((LinearLayout) view.findViewById(R.id.incl)).findViewById(R.id.tvSearch));

            leftAdapter = new ClassLeftAdapter(getContext(), data);
            lvClass.setAdapter(leftAdapter);
            rvClass.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
            rvClass.setAdapter(rvAdapter);
        }

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = etSearch.getText().toString().trim();
                Intent intent = new Intent(getContext(), QueryActivity.class);
                intent.putExtra("keyWord", keyWord);
                startActivity(intent);
            }
        });


        try {
            lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < lvClass.getChildCount(); i++) {
                        lvClass.getChildAt(i).setBackgroundResource(R.drawable.shape_class_left_normal);
                    }

                    view.setBackgroundResource(R.drawable.shape_class_left_selected);
                    leftAdapter.setCurrentPosition(position);

                    List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> beanList = map.get(data[position]);
                    Log.e(TAG, "onItemClick: data[position]/beanList="+data[position]+"/"+beanList );
                    if (beanList == null || beanList.size() <= 0) {
                        initData(data[position]);//如果没有则从网络下载
                    } else {
                        list.clear();
                        list.addAll(beanList);
                        rvAdapter.notifyDataSetChanged();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: e=" + e.getMessage());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        lvClass.getChildAt(0).setBackgroundResource(R.drawable.shape_class_left_selected);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(netWorkReceiver);
    }

    @Override
    public void networkIsAvailable() {
        initData(data[0]);
    }
}
