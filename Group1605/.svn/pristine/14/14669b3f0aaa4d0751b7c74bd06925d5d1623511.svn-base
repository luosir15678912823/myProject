package com.nightly.lovetravel.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Nightly on 2016/10/8.
 */

public class HttpUtil {
    public static final String APP_ID = "24885";
//    public static final String APP_ID = "25616";

    public static final String APP_SIGN = "59a814c5da6a450b96e951786ee8df57";
//    public static final String APP_SIGN = "6b6d60126327da85fc065e1bc56b8277";
    private static OkHttpClient okHttpClient;
    private final static String TAG = "Test";

    /**
     * 根据关键字搜索景点
     *
     * @param keyword
     * @return
     */
    public static String getAttractions(String areaId,String cityId,String proId,String keyword, int page) {
        String timestamp = DateTimeUtil.getTimestampLong();
        String url = "https://route.showapi.com/268-1?areaId="+areaId+"&cityId="+cityId+"&keyword=" + keyword + "&page=" + page + "&proId="+proId+"&showapi_appid=" + APP_ID + "&showapi_timestamp=" + timestamp + "&showapi_sign=" + APP_SIGN + "";
        return getStringByOkHttp(url);
    }
    /**
     * 根据关键字搜索景点
     *
     * @param keyword
     * @return
     */
    public static String getAttractions(String keyword, int page) {
        return getAttractions("","","",keyword,page);
    }

    /**
     * 根据经纬度查询地址
     *
     * @param latitude  经度
     * @param longitude 纬度
     * @return
     */
    public static String getAddress(double latitude, double longitude) {
        String timestamp = DateTimeUtil.getTimestampLong();
        String url = "https://route.showapi.com/238-2?from=5&lat=" + latitude + "&lng=" + latitude + "&showapi_appid=" + APP_ID + "&showapi_timestamp=" + timestamp + "&showapi_sign=" + APP_SIGN + "";
        return getStringByOkHttp(url);
    }

    /**
     * 酒店接口，根据名称查行政区，用于获取城市id，查询酒店使用
     * @param divisionName
     * @return
     */
    public static String getCity(String divisionName){
        String timestamp = DateTimeUtil.getTimestampLong();
        String url="https://route.showapi.com/405-6?divisionName="+divisionName+"&showapi_appid="+APP_ID+"&showapi_timestamp="+timestamp+"&showapi_sign="+APP_SIGN+"";
        return getStringByOkHttp(url);
    }

    public static String getHotels(String cityId,String comeDate,String leaveDate,String sectionId,String keyword,String longitude,String latitude,String hbs,String starRatedId,String sortType,String page,String pageSize){
        String timestamp = DateTimeUtil.getTimestampLong();
        String url="https://route.showapi.com/405-5?cityId="+cityId+"&comeDate="+comeDate+"&hbs=&keyword="+keyword+"&latitude="+latitude+"&leaveDate="+leaveDate+"&longitude="+longitude+"&page="+page+"&pageSize="+pageSize+"&sectionId="+sectionId+"&showapi_appid="+APP_ID+"&showapi_timestamp="+timestamp+"&sortType=&starRatedId=&showapi_sign="+APP_SIGN+"";
        Log.e(TAG, "getHotels: url="+url);
        return getStringByOkHttp(url);
    }
    public static String getHotels(String cityId,String comeDate,String leaveDate,String longitude,String latitude,String page,String pageSize){
        String timestamp = DateTimeUtil.getTimestampLong();
        return getHotels(cityId,comeDate,leaveDate,"","",longitude,latitude,"","","",page,pageSize);
    }

    /**
     *
     * @param areaid
     * @param area
     * @param needMoreDay
     * @param needIndex
     * @param needHourData
     * @param need3HourForcast
     * @param needAlarm
     * @return
     */
    public static String getWeather(String areaid,String area,String needMoreDay,String needIndex,String needHourData,String need3HourForcast,String needAlarm){
        String timestamp = DateTimeUtil.getTimestampLong();
        String url="https://route.showapi.com/9-2?area="+area+"&areaid="+areaid+"&need3HourForcast="+need3HourForcast+"&needAlarm="+needAlarm+"&needHourData="+needHourData+"&needIndex="+needIndex+"&needMoreDay="+needMoreDay+"&showapi_appid="+APP_ID+"&showapi_timestamp="+timestamp+"&showapi_sign="+APP_SIGN+"";
//        Log.e(TAG, "getWeather: url="+url);
        return getStringByOkHttp(url);
    }

    /**
     *
     * @param area  地区名
     * @param needMoreDay   是否需要返回最后四至七天数据--默认返回后三天数据，1代表返回，0代表不返回
     * @param needIndex 是否需要返回穿衣指数等指数数据--- 1代表返回，0代表不返回
     * @param needAlarm
     * @return
     */
    public static String getWeather(String area,String needMoreDay,String needIndex,String needAlarm){
        return getWeather("",area,needMoreDay,needIndex,"","",needAlarm);
    }

    /**
     * 根据url获取json字符串
     * @param url
     * @return
     */
    @NonNull
    public static String getStringByOkHttp(String url) {
        String json = "";
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        Request request = new Request.Builder()
                .tag("AttractionsBean")
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                json = response.body().string();
            } else {
                json = "response is not successful";
            }
        } catch (IOException e) {
            json = "IOException:"+e;
            e.printStackTrace();
        }
        return json;
    }


}
