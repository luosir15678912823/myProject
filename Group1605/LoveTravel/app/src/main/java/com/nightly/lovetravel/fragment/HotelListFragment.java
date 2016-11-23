package com.nightly.lovetravel.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.adapter.HotelListAdapter;
import com.nightly.lovetravel.bean.CityHotelBean;
import com.nightly.lovetravel.util.DateTimeUtil;
import com.nightly.lovetravel.util.HttpUtil;
import com.nightly.lovetravel.util.JsonUtil;
import com.nightly.lovetravel.util.ThreadUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nightly on 2016/11/2.
 */

public class HotelListFragment extends Fragment implements HotelListAdapter.onItemClickListener{

    private static final int HOTEL_INFO_GOT = 1;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.rvHotel)
    RecyclerView rvHotel;
    private View view;

    private CityHotelBean cityHotelBean;
    private HotelListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Fragment fragment;
    private List<CityHotelBean.ShowapiResBodyBean.ListBean> data;
    private HotelDetailFragment hotelDetailFragment;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HOTEL_INFO_GOT:
                    String json = (String) msg.obj;
                    cityHotelBean = JsonUtil.parseToCityHotelBean(json);
                    dealHotelData(cityHotelBean);
                    break;
            }
        }
    };
    public void setCityHotelBean(CityHotelBean cityHotelBean) {
        this.cityHotelBean = cityHotelBean;
    }

    public void setFragment(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if(data==null&&bundle!=null){
            ThreadUtil.execute(new Runnable() {
                @Override
                public void run() {
                    String cityId = bundle.getString("cityId");
                    String lon = bundle.getString("lon");
                    String lat = bundle.getString("lat");
                    //假设今天入住明天离开，否则查询不到数据
                    String comeDate = DateTimeUtil.getTimestampShort();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String specifiedDay = sdf.format(date);
//                    Log.e(TAG, "run: 今天="+specifiedDay+",明天="+DateTimeUtil.getSpecifiedDayAfter(specifiedDay));
                    String leaveDate = DateTimeUtil.getSpecifiedDayAfter(specifiedDay);
                    String page = "1";
                    String pageSize = "20";
                    String json = HttpUtil.getHotels(cityId, comeDate, leaveDate, lon, lat, page, pageSize);
                    sendJsonMessage(json, HOTEL_INFO_GOT);
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_hotel_list, container, false);
            ButterKnife.bind(this, view);
        }
        if(cityHotelBean!=null) {
            dealHotelData(cityHotelBean);
        }
        return view;
    }

    private void dealHotelData(CityHotelBean cityHotelBean) {
        data = cityHotelBean.getShowapi_res_body().getList();
        adapter = new HotelListAdapter(getContext(), data);
        adapter.setListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHotel.setAdapter(adapter);
        rvHotel.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.tvBack)
    public void onTvBackClick(View view) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_left_to_right_enter,R.anim.fragment_left_to_right_exit)
                .replace(R.id.fm,fragment)
                .commit();
    }


    @Override
    public void toHotelDetail(int position) {
        CityHotelBean.ShowapiResBodyBean.ListBean listBean = data.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", listBean.getHotelName());
        bundle.putString("url", listBean.getImg());
        bundle.putString("commentGood", listBean.getCommentGood());
        bundle.putString("commentTotal", listBean.getCommentTotal());
        bundle.putString("address", listBean.getAddress());
        bundle.putString("nearby", listBean.getNearBy());
        bundle.putString("hotelType", listBean.getRoomType());
        bundle.putString("level", listBean.getStarRatedName());
        bundle.putString("intro", listBean.getIntro());
        bundle.putString("oneWord", listBean.getOneWord());
        bundle.putString("price", listBean.getLowestPrice());
        bundle.putString("longitude",listBean.getLongitude());
        bundle.putString("latitude",listBean.getLatitude());

        hotelDetailFragment = new HotelDetailFragment();
        hotelDetailFragment.setBundle(bundle);
        hotelDetailFragment.setFragment(this);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_to_left_enter,R.anim.fragment_right_to_left_exit)
                .replace(R.id.fm,hotelDetailFragment)
                .commit();

    }
    /**
     * 给handler发送消息
     *
     * @param json
     * @param what
     */
    private void sendJsonMessage(String json, int what) {
        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = json;
        handler.sendMessage(message);
    }
}
