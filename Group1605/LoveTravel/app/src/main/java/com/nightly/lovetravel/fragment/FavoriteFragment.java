package com.nightly.lovetravel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nightly.lovetravel.MainActivity;
import com.nightly.lovetravel.R;
import com.nightly.lovetravel.adapter.FavoriteResultAdapter;
import com.nightly.lovetravel.bean.AttractionsBean;
import com.nightly.lovetravel.bean.ContentBean;
import com.nightly.lovetravel.bean.User;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Nightly on 2016/10/21.
 */

public class FavoriteFragment extends Fragment implements FavoriteResultAdapter.OnItemClickListener{

    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.ptrResult)
    PullToRefreshListView ptrResult;
    @BindView(R.id.pb)
    ProgressBar pb;
    private View view;
    List<ContentBean> data=new ArrayList<>();
    private FavoriteResultAdapter adapter;
    final String TAG="test";
    private HotelListFragment hotelListFragment;
    private DetailFragment detailFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(getContext())
                //设置appkey
                .setApplicationId("2d254141444030d2b8b7e5b1eb36b7ef")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(10)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        User user = BmobUser.getCurrentUser(User.class);
        if (user==null){
            return;
        }
        String objectId = user.getObjectId();

        BmobQuery<ContentBean> query=new BmobQuery<>();
        query.addWhereEqualTo("userId",objectId);
        query.findObjects(new FindListener<ContentBean>() {
            @Override
            public void done(List<ContentBean> list, BmobException e) {
                Log.e(TAG, "done: e/list="+e+"/"+list );
                if (e==null){
                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_favorite, container, false);
        }

        ButterKnife.bind(this, view);

        adapter = new FavoriteResultAdapter(getContext(),data);
        adapter.setListener(this);
        ptrResult.setAdapter(adapter);
        return view;
    }

    @OnClick(R.id.tvBack)
    public void onTvBackClick(){
        EventBus.getDefault().post(MainActivity.SHOW_MAINFRAGMENT);
    }

    @Override
    public void OnTvHotelClick(int position) {

        if (hotelListFragment == null) {
            hotelListFragment = new HotelListFragment();
        }
        final AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = data.get(position).getContentlistBean();
        String cityId = contentlistBean.getCityId();
        AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.LocationBean location = contentlistBean.getLocation();
        String lon = location.getLon();
        String lat = location.getLat();
        Bundle bundle = new Bundle();
        bundle.putString("cityId", cityId);
        bundle.putString("lon", lon);
        bundle.putString("lat", lat);
        hotelListFragment.setArguments(bundle);
        hotelListFragment.setCityHotelBean(null);
        hotelListFragment.setFragment(this);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_to_left_enter, R.anim.fragment_right_to_left_exit)
                .replace(R.id.fm, hotelListFragment)
                .commit();

    }

    @Override
    public void OnTvDetailClick(int position) {
        AttractionsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = data.get(position).getContentlistBean();
        String objectId = data.get(position).getObjectId();
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
}
