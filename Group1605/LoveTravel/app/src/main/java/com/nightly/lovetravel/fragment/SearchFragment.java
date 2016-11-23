package com.nightly.lovetravel.fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nightly.lovetravel.NetworkReceiver;
import com.nightly.lovetravel.R;
import com.nightly.lovetravel.adapter.SearchResultAdapter;
import com.nightly.lovetravel.bean.AttractionsBean;
import com.nightly.lovetravel.util.HttpUtil;
import com.nightly.lovetravel.util.JsonUtil;
import com.nightly.lovetravel.util.ThreadUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nightly on 2016/10/21.
 * 搜索结果页面
 */

public class SearchFragment extends Fragment implements SearchResultAdapter.OnItemClickListener,NetworkReceiver.NetWorkChangeListener {
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.edKeyword)
    EditText edKeyword;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.tvNetworkState)
    TextView tvNetworkState;
    @BindView(R.id.ptrResult)
    PullToRefreshListView ptrResult;
    @BindView(R.id.pb)
    ProgressBar pb;
    private View view;
    private final int MSG_JSON_GOT = 1;
    private final String TAG = "test";
    private int currentPage = 1;//默认加载第一页
    private String keyword;//搜索关键字
    private MainFragment mainFragment;//设置进来的主页面，用于tvBack触发返回主页面事件
    private SearchResultAdapter searchResultAdapter;
    private List<AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data = null;//搜索结果
    private DetailFragment detailFragment;
    private HotelListFragment hotelListFragment;
    private NetworkReceiver networkReceiver;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_JSON_GOT:
                    dealJson(msg);
                    pb.setVisibility(View.GONE);
                    break;
            }
        }
    };

    public void setMainFragment(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkReceiver = new NetworkReceiver();
        getActivity().registerReceiver(networkReceiver, new IntentFilter(NetworkReceiver.NETWORK_INTENT_ACTION));
        networkReceiver.setListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_search, container, false);
            ButterKnife.bind(this, view);
        }
        searchResultAdapter = new SearchResultAdapter(getActivity(), data);
        ptrResult.setAdapter(searchResultAdapter);

        setPtrResultAttrs();
        pb.setVisibility(View.GONE);

        ptrResult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                search(keyword, currentPage);
                ptrResult.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载下一页
                search(keyword, ++currentPage);
                ptrResult.onRefreshComplete();

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(networkReceiver);
    }

    /**
     * 给ptrResult设置属性
     */
    private void setPtrResultAttrs() {
        ptrResult.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout loadingLayoutProxy = ptrResult.getLoadingLayoutProxy();
        loadingLayoutProxy.setPullLabel("下拉刷新");
        loadingLayoutProxy.setReleaseLabel("释放以更新数据");
        loadingLayoutProxy.setRefreshingLabel("正在加载");
        loadingLayoutProxy.setLastUpdatedLabel("上次更新 " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.refresh_big));
    }

    /**
     * json处理
     *
     * @param msg
     */
    private void dealJson(Message msg) {
        String json = (String) msg.obj;
        AttractionsBean attractionsBean = JsonUtil.parseAttractionsBean(json);
        Log.e(TAG, "handleMessage: attractionsBean:" + attractionsBean);
        if (attractionsBean != null && attractionsBean.getShowapi_res_body().getPagebean() != null && attractionsBean.getShowapi_res_body().getPagebean().getContentlist() != null) {
            if (data == null) {
                data = new ArrayList<>();
            }
            data.addAll(attractionsBean.getShowapi_res_body().getPagebean().getContentlist());
            searchResultAdapter = new SearchResultAdapter(getActivity(), data);
            searchResultAdapter.setListener(this);
            ptrResult.setAdapter(searchResultAdapter);

//          searchResultAdapter.notifyDataSetChanged();
            if (currentPage > 1) {
                ptrResult.getRefreshableView().setSelection(data.size() - 1);//滚动到页面底部
            }
        } else {
            Toast.makeText(getActivity(), "出了点小问题，稍等一下哈...", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
        }
        /**
         * item进入动画
         */
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getContext(), R.anim.leftward_enter), 0.2f);
        ptrResult.setLayoutAnimation(lac);
    }

    /**
     * 搜索按钮事件
     *
     * @param view
     */
    @OnClick(R.id.tvSearch)
    public void onTvSearchClick(View view) {
        keyword = edKeyword.getText().toString().trim();
        Log.e(TAG, "onTvSearchClick: " + keyword);
        if (keyword == null) {
            Snackbar.make(view, "请输入搜索条件。", Snackbar.LENGTH_LONG).show();
            return;
        }
        pb.setVisibility(View.VISIBLE);
        search(keyword, currentPage);
    }

    /**
     * 景点搜索
     *
     * @param keyword
     * @param page
     */
    private void search(final String keyword, final int page) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getAttractions(keyword, page);
                Message message = handler.obtainMessage();
                message.what = MSG_JSON_GOT;
                message.obj = json;
                Log.e(TAG, "run: json:" + json);
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 返回
     *
     * @param view
     */
    @OnClick(R.id.tvBack)
    public void onTvBackClick(View view) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_left_to_right_enter,R.anim.fragment_left_to_right_exit)
                .replace(R.id.fm, mainFragment)
                .commit();
    }
    /**
     * 查看附近酒店按钮
     *
     * @param position
     */
    @Override
    public void OnTvHotelClick(int position) {
        Log.e(TAG, "OnTvDetailClick: 搜索页面查看附近酒店按钮");
        if(hotelListFragment==null) {
            hotelListFragment = new HotelListFragment();
        }
        final AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = data.get(position);
        String cityId = contentlistBean.getCityId();
        AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.LocationBean location = contentlistBean.getLocation();
        String lon = location.getLon();
        String lat = location.getLat();
        Bundle bundle = new Bundle();
        bundle.putString("cityId",cityId);
        bundle.putString("lon",lon);
        bundle.putString("lat",lat);
        hotelListFragment.setArguments(bundle);
        hotelListFragment.setCityHotelBean(null);
        hotelListFragment.setFragment(this);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_to_left_enter, R.anim.fragment_right_to_left_exit)
                .replace(R.id.fm, hotelListFragment)
                .commit();
    }
    /**
     * 查看景点详情按钮
     *
     * @param position
     */
    @Override
    public void OnTvDetailClick(int position) {
        Log.e(TAG, "OnTvDetailClick: 搜索页面详情按钮");
        AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = data.get(position);
        if (detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        detailFragment.setData(contentlistBean);
        detailFragment.setFragment(this);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_to_left_enter, R.anim.fragment_right_to_left_exit)
                .replace(R.id.fm, detailFragment)
                .commit();
    }

    @Override
    public void NetWorkChange(boolean isAvailable) {
        if(!isAvailable){
            tvNetworkState.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "网络不可用，请检查网络设置...", Toast.LENGTH_SHORT).show();
        }else {
            tvNetworkState.setVisibility(View.GONE);
        }
    }
}
