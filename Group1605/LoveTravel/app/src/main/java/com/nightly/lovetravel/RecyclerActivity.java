package com.nightly.lovetravel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nightly.lovetravel.adapter.TourGridAdapter;
import com.nightly.lovetravel.adapter.TourListAdapter;
import com.nightly.lovetravel.bean.AttractionsBean;
import com.nightly.lovetravel.util.HttpUtil;
import com.nightly.lovetravel.util.JsonUtil;
import com.nightly.lovetravel.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity {
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.activity_recycler)
    LinearLayout activityRecycler;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private TourListAdapter tourListAdapter;
    private TourGridAdapter tourGridAdapter;
    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data=new ArrayList<>();
    public static final int DATA_GOT=1;
    private final int LIST_ADAPTER=1;
    private final int GRID_ADAPTER=2;
    private int currentAdapter=LIST_ADAPTER;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DATA_GOT:
                    Bundle bundle = msg.getData();
                    String json = (String) bundle.get("json");
                    AttractionsBean attractionsBean = JsonUtil.parseAttractionsBean(json);
                    data=attractionsBean.getShowapi_res_body().getPagebean().getContentlist();
                    if(data==null){
                        Toast.makeText(RecyclerActivity.this, "出错了，稍等一下。", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(currentAdapter==LIST_ADAPTER){
                        tourListAdapter.notifyDataSetChanged();
                    }else {
                        tourGridAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayout.VERTICAL, false);

        initData();
        tourListAdapter = new TourListAdapter(this,data);
        tourGridAdapter = new TourGridAdapter(this,data);
        useLinearLayout();//默认详细列表
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        if(currentAdapter==GRID_ADAPTER) {
                            useLinearLayout();
                            currentAdapter = LIST_ADAPTER;
                        }
                        break;
                    case R.id.rb2:
                        if(currentAdapter==LIST_ADAPTER) {
                            useGridLayout();
                            currentAdapter = GRID_ADAPTER;
                        }
                        break;
                }
            }
        });
    }

    private void initData() {
        ThreadUtil.execute(new Runnable() {


            @Override
            public void run() {
                String keyword = "秦皇岛";
                String json = HttpUtil.getAttractions(keyword,1);
                Message message = handler.obtainMessage();
                message.what=DATA_GOT;
                Bundle bundle = new Bundle();
                bundle.putString("json",json);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }

    public void useLinearLayout(){
        rv.setAdapter(tourListAdapter);
        rv.setLayoutManager(linearLayoutManager);
    }
    public void useGridLayout(){
        rv.setAdapter(tourListAdapter);
        rv.setLayoutManager(linearLayoutManager);
    }

}
