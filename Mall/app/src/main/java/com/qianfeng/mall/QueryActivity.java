package com.qianfeng.mall;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.mall.adapter.QueryAdapter;
import com.qianfeng.mall.modle.QueryBean;
import com.qianfeng.mall.util.HttpUtil;
import com.qianfeng.mall.util.JsonUtil;
import com.qianfeng.mall.util.ThreadUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private static final int MSG_GOT_QUERY_DATA = 10;
    private static final String TAG = "test";
    private PullToRefreshListView ptrlv;
    private List<QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean> list = new ArrayList<>();
    private QueryAdapter adapter;
    private TextView tvSearch,tvBack;
    EditText etSearch;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case MSG_GOT_QUERY_DATA:
                    String json = (String) msg.obj;
                    QueryBean queryBean = JsonUtil.parseQueryBean(json);

                    if (queryBean == null || queryBean.getShowapi_res_body() == null || queryBean.getShowapi_res_body().getPageBean() == null || queryBean.getShowapi_res_body().getPageBean().getContentlist() == null || queryBean.getShowapi_res_body().getPageBean().getContentlist().size() <= 0) {
                        Log.e(TAG, "handleMessage: queryBean="+queryBean );
                        if (currentPage == 1) {
                            Toast.makeText(QueryActivity.this, "没有找到该类物品", Toast.LENGTH_SHORT).show();
                        }if(currentPage>1){
                            Toast.makeText(QueryActivity.this, "没有更多的商品", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                    if (currentPage==1){
                        list.clear();
                    }
                    list.addAll(queryBean.getShowapi_res_body().getPageBean().getContentlist());
                    adapter.notifyDataSetChanged();
                    if (currentPage == 1) {
                        ptrlv.getRefreshableView().setSelection(0);
                    }

                    break;
            }
        }
    };
    private ILoadingLayout loadingLayoutProxy;
    private String name;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);


        ptrlv = ((PullToRefreshListView) findViewById(R.id.ptrlv));


        tvSearch = ((TextView) ((LinearLayout) findViewById(R.id.incl)).findViewById(R.id.tvSearch));
        etSearch = ((EditText) ((LinearLayout) findViewById(R.id.incl)).findViewById(R.id.etSearch));
        tvBack = ((TextView) ((LinearLayout) findViewById(R.id.incl)).findViewById(R.id.tvBack));

        final String keyWord = getIntent().getStringExtra("keyWord");
        initData(keyWord, 1);

        adapter = new QueryAdapter(this, list);
        ptrlv.setAdapter(adapter);
        ptrlv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        loadingLayoutProxy = ptrlv.getLoadingLayoutProxy();
        loadingLayoutProxy.setLastUpdatedLabel(" 上次刷新：" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.refresh));
//        loadingLayoutProxy.setPullLabel("pull lable");
        loadingLayoutProxy.setRefreshingLabel("正在刷新，请耐心等待.....");
//        loadingLayoutProxy.setReleaseLabel("release lable");
        loadingLayoutProxy.setTextTypeface(Typeface.DEFAULT);


        ptrlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPage++;
                        initData(name, currentPage);
                        Log.e(TAG, "run: currentPage="+currentPage );
                        ptrlv.onRefreshComplete();
                    }
                }, 3000);


            }
        });


        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etSearch.getText().toString().trim();
                currentPage = 1;
                initData(name, currentPage);
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void initData(final String keyWord, final int page) {
        if ((keyWord == null || keyWord.length() <= 0)&&currentPage==1) {
            return;
        }
        ThreadUtil.doHttpByExecutePool(new Runnable() {
            @Override
            public void run() {
                String json = "";
                json = HttpUtil.getQueryBean(keyWord, page);
                Log.e(TAG, "run: json=" + json);
                Message message = Message.obtain();
                message.obj = json;
                message.what = MSG_GOT_QUERY_DATA;
                handler.sendMessage(message);
            }
        });
    }
}
