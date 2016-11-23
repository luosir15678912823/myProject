package com.nightly.lovetravel.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.WeatherBean;
import com.nightly.lovetravel.customview.AqiView;
import com.nightly.lovetravel.util.ThreadUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by Nightly on 2016/11/2.
 */

public class WeatherFragment extends Fragment {
    private static final String TAG = "test";
    private Fragment fragment;
    @BindView(R.id.tvWeatherBar1)
    TextView tvWeatherBar1;
    @BindView(R.id.tvWeatherBar2)
    TextView tvWeatherBar2;
    @BindView(R.id.tvWeatherTemperature)
    TextView tvWeatherTemperature;
    @BindView(R.id.tvWeatherDay)
    TextView tvWeatherDay;
    @BindView(R.id.tvWeatherMass)
    TextView tvWeatherMass;
    @BindView(R.id.a1)
    LinearLayout a1;
    @BindView(R.id.ivWeatherPic)
    ImageView ivWeatherPic;
    @BindView(R.id.tvWeatherB)
    TextView tvWeatherB;
    @BindView(R.id.tvWeatherWind)
    TextView tvWeatherWind;
    @BindView(R.id.llNowWeather)
    LinearLayout llNowWeather;
    @BindView(R.id.llLastWeather)
    LinearLayout llLastWeather;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.av)
    AqiView av;
    @BindView(R.id.tvPM10)
    TextView tvPM10;
    @BindView(R.id.tvPM25)
    TextView tvPM25;
    @BindView(R.id.tvNO2)
    TextView tvNO2;
    @BindView(R.id.tvSO2)
    TextView tvSO2;
    @BindView(R.id.tvO3)
    TextView tvO3;
    @BindView(R.id.tvCO)
    TextView tvCO;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.av_Comfortable)
    AqiView avComfortable;
    @BindView(R.id.tvAqiPic)
    TextView tvAqiPic;
    @BindView(R.id.tvQualityPic)
    TextView tvQualityPic;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvHumidity2)
    TextView tvHumidity2;
    @BindView(R.id.tvBodyTemperature)
    TextView tvBodyTemperature;
    @BindView(R.id.tvSun)
    TextView tvSun;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private View view;

    private WeatherBean weatherBean;
    private Handler handler = new Handler();
    private Handler sonHandler;
    private int aqi;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_weather, container, false);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ButterKnife.bind(this, view);
            setEvent();
        }
        initData();
        ButterKnife.bind(this, view);
        return view;
    }

    private void setEvent() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipe.isRefreshing()) {
                            swipe.setRefreshing(false);
                        }
                    }
                }, 3000);
            }
        });
    }

    private void initData() {
        WeatherBean.ShowapiResBodyBean body = weatherBean.getShowapi_res_body();
        WeatherBean.ShowapiResBodyBean.CityInfoBean cityInfo = body.getCityInfo();
        WeatherBean.ShowapiResBodyBean.NowBean now = body.getNow();
        WeatherBean.ShowapiResBodyBean.F1Bean f1 = body.getF1();
        WeatherBean.ShowapiResBodyBean.F2Bean f2 = body.getF2();
        WeatherBean.ShowapiResBodyBean.F3Bean f3 = body.getF3();
        WeatherBean.ShowapiResBodyBean.F4Bean f4 = body.getF4();
        WeatherBean.ShowapiResBodyBean.F5Bean f5 = body.getF5();
        WeatherBean.ShowapiResBodyBean.F6Bean f6 = body.getF6();
        WeatherBean.ShowapiResBodyBean.F7Bean f7 = body.getF7();
        WeatherBean.ShowapiResBodyBean.NowBean.AqiDetailBean aqiDetail = now.getAqiDetail();

        //初始化当天数据
        nowWeather(body, cityInfo, now, f1, aqiDetail);

        //初始化未来七天数据
        lastWeather(f1, f2, f3, f4, f5, f6, f7);

        //初始化污染指数
        quality(aqiDetail);
        //初始化舒适度
        well(now, f1);

    }

    private void well(WeatherBean.ShowapiResBodyBean.NowBean now, WeatherBean.ShowapiResBodyBean.F1Bean f1) {
        String sd = now.getSd();
        WeatherBean.ShowapiResBodyBean.F1Bean.IndexBean index = f1.getIndex();
        WeatherBean.ShowapiResBodyBean.F1Bean.IndexBean.UvBean uv = index.getUv();
        String desc = uv.getTitle();
        Log.e(TAG, "紫外线: " + desc);
        tvHumidity.setText(sd);
        tvSun.setText(desc);
        avComfortable.setK(new Integer(sd.substring(0, sd.length() - 1)).intValue());
        avComfortable.postInvalidate();
    }

    private void quality(WeatherBean.ShowapiResBodyBean.NowBean.AqiDetailBean aqiDetail) {
        int pm10 = aqiDetail.getPm10();
        int pm2_5 = aqiDetail.getPm2_5();
        int no2 = aqiDetail.getNo2();
        int so2 = aqiDetail.getSo2();
        int o3 = aqiDetail.getO3();
        double co = aqiDetail.getCo();
        aqi = aqiDetail.getAqi();
        String quality = aqiDetail.getQuality();
        tvPM10.setText(pm10 + "");
        tvPM25.setText(pm2_5 + "");
        tvNO2.setText(no2 + "");
        tvSO2.setText(so2 + "");
        tvO3.setText(o3 + "");
        tvCO.setText(co + "");
        tvAqiPic.setText(aqi + "");
        tvQualityPic.setText(quality);
        final int i = aqi;
        updataAqi(i);
        av.postInvalidate();
    }

    /**
     * 更新aqi的原点数据,动态效果
     * @param i
     */
    private void updataAqi(final int i) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                int j = 0;
                if (handler != null) {
                    startThread(handler, i, j);
                }
            }
        });
    }

    private void startThread(final Handler handler, final int i, int j) {
        Log.e(TAG, "startThread: " + i + "/" + j);
        if (j > i) {
            return;
        }
        final int finalJ = j;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int a = finalJ;
                Log.e(TAG, "run: " + finalJ);
                av.setI(finalJ);
                av.postInvalidate();
                startThread(handler, i, ++a);
            }
        }, 20);
    }

    private void lastWeather(WeatherBean.ShowapiResBodyBean.F1Bean f1, WeatherBean.ShowapiResBodyBean.F2Bean f2, WeatherBean.ShowapiResBodyBean.F3Bean f3, WeatherBean.ShowapiResBodyBean.F4Bean f4, WeatherBean.ShowapiResBodyBean.F5Bean f5, WeatherBean.ShowapiResBodyBean.F6Bean f6, WeatherBean.ShowapiResBodyBean.F7Bean f7) {
        LayoutInflater from = LayoutInflater.from(getContext());

        View v1 = from.inflate(R.layout.item_weather_info, null, false);
        Log.e(TAG, "initData: " + v1);
        ((TextView) v1.findViewById(R.id.tvWeatherDate)).setText("今天");
        Picasso.with(getContext()).load(f1.getDay_weather_pic()).into(((ImageView) v1.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f1.getDay_weather_pic()).into(((ImageView) v1.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v1.findViewById(R.id.tvWeatherInfoT)).setText(f1.getDay_air_temperature() + "°" + "/" + f1.getNight_air_temperature() + "°");

        View v2 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v2.findViewById(R.id.tvWeatherDate)).setText("明天");
        Picasso.with(getContext()).load(f2.getDay_weather_pic()).into(((ImageView) v2.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f2.getDay_weather_pic()).into(((ImageView) v2.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v2.findViewById(R.id.tvWeatherInfoT)).setText(f2.getDay_air_temperature() + "°" + "/" + f2.getNight_air_temperature() + "°");

        View v3 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v3.findViewById(R.id.tvWeatherDate)).setText("后天");
        Picasso.with(getContext()).load(f3.getDay_weather_pic()).into(((ImageView) v3.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f3.getDay_weather_pic()).into(((ImageView) v3.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v3.findViewById(R.id.tvWeatherInfoT)).setText(f3.getDay_air_temperature() + "°" + "/" + f3.getNight_air_temperature() + "°");

        View v4 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v4.findViewById(R.id.tvWeatherDate)).setText(getWeekString(f4.getWeekday()));
        Picasso.with(getContext()).load(f4.getDay_weather_pic()).into(((ImageView) v4.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f4.getDay_weather_pic()).into(((ImageView) v4.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v4.findViewById(R.id.tvWeatherInfoT)).setText(f4.getDay_air_temperature() + "°" + "/" + f4.getNight_air_temperature() + "°");

        View v5 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v5.findViewById(R.id.tvWeatherDate)).setText(getWeekString(f5.getWeekday()));
        Picasso.with(getContext()).load(f5.getDay_weather_pic()).into(((ImageView) v5.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f5.getDay_weather_pic()).into(((ImageView) v5.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v5.findViewById(R.id.tvWeatherInfoT)).setText(f5.getDay_air_temperature() + "°" + "/" + f5.getNight_air_temperature() + "°");

        View v6 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v6.findViewById(R.id.tvWeatherDate)).setText(getWeekString(f6.getWeekday()));
        Picasso.with(getContext()).load(f6.getDay_weather_pic()).into(((ImageView) v6.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f6.getDay_weather_pic()).into(((ImageView) v6.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v6.findViewById(R.id.tvWeatherInfoT)).setText(f6.getDay_air_temperature() + "°" + "/" + f6.getNight_air_temperature() + "°");

        View v7 = from.inflate(R.layout.item_weather_info, null, false);
        ((TextView) v7.findViewById(R.id.tvWeatherDate)).setText(getWeekString(f7.getWeekday()));
        Picasso.with(getContext()).load(f7.getDay_weather_pic()).into(((ImageView) v7.findViewById(R.id.ivWeatherDayIco)));
        Picasso.with(getContext()).load(f7.getDay_weather_pic()).into(((ImageView) v7.findViewById(R.id.ivWeatherNightIco)));
        ((TextView) v7.findViewById(R.id.tvWeatherInfoT)).setText(f7.getDay_air_temperature() + "°" + "/" + f7.getNight_air_temperature() + "°");

        //添加到布局
        llLastWeather.addView(v1);
        llLastWeather.addView(v2);
        llLastWeather.addView(v3);
        llLastWeather.addView(v4);
        llLastWeather.addView(v5);
        llLastWeather.addView(v6);
        llLastWeather.addView(v7);
    }

    private void nowWeather(WeatherBean.ShowapiResBodyBean body, WeatherBean.ShowapiResBodyBean.CityInfoBean cityInfo, WeatherBean.ShowapiResBodyBean.NowBean now, WeatherBean.ShowapiResBodyBean.F1Bean f1, WeatherBean.ShowapiResBodyBean.NowBean.AqiDetailBean aqiDetail) {
        String cityName = cityInfo.getC3();
        String time = body.getTime();
        String s = time.substring(4, 10);
        String month = s.substring(0, 2);
        String day = s.substring(2, 4);
        String hour = s.substring(4, 6);
        String temperature = now.getTemperature();
        String day_night = f1.getDay_air_temperature() + "°" + "/" + f1.getNight_air_temperature() + "°";
        String quality = aqiDetail.getQuality();
        String weather_pic = now.getWeather_pic();
        String weather = now.getWeather();
        String wind_power = now.getWind_power();
        tvWeatherBar1.setText(cityName);
        tvWeatherBar2.setText(month + "月" + day + "日" + hour + "时" + "前更新");
        tvWeatherTemperature.setText(temperature + "°");
        tvWeatherDay.setText(day_night);
        tvWeatherMass.setText(quality);
        Picasso.with(getContext()).load(weather_pic).into(ivWeatherPic);
        tvWeatherB.setText(weather);
        tvWeatherWind.setText(wind_power);
    }

    private String getWeekString(int i) {
        Log.e(TAG, "getWeekString: " + i);
        String str = null;
        switch (i) {
            case 1:
                str = "周一";
                break;
            case 2:
                str = "周二";
                break;
            case 3:
                str = "周三";
                break;
            case 4:
                str = "周四";
                break;
            case 5:
                str = "周五";
                break;
            case 6:
                str = "周六";
                break;
            case 7:
                str = "周日";
                break;
        }
        return str;

    }

    @OnClick(R.id.tvBack)
    public void backOn() {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fragment_right_to_left_enter, R.anim.fragment_right_to_left_exit);
            fragmentTransaction.replace(R.id.fm, fragment).commit();
        }

    }

}
