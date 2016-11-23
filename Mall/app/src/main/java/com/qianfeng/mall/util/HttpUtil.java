package com.qianfeng.mall.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by qf on 2016/10/8.
 */
public class HttpUtil {


    private static int APPID = 24868;
    private static String   API_SIGN = "5bebe54caaa74888b4492a32784675a7";


    public static String getStringByGroupBuyToday() {

        String url= "https://route.showapi.com/907-4?showapi_appid=" + APPID + "&showapi_sign=" + API_SIGN;
        String json = getStringJsonByOkHttp(url);
        return json;

    }


    public static String getQueryBean(String cid){

        String url= " http://route.showapi.com/907-2? cid="+cid+"&keyword=&page=1&showapi_appid="+APPID+"&showapi_sign="+API_SIGN;
        String json = getStringJsonByOkHttp(url);
        return json;
    }
//    public static String getQueryBean(String keyword){
//
//        return getQueryBean(keyword,1);
//    }
    public static String getQueryBean(String keyword,int page){

        String url= " http://route.showapi.com/907-2?&keyword="+keyword+"&page="+page+"&showapi_appid="+APPID+"&showapi_sign="+API_SIGN;
        String json = getStringJsonByOkHttp(url);
        return json;
    }

    public static String getAdvertisment(){

//        http://route.showapi.com/907-3?showapi_appid=24868&showapi_sign=5bebe54caaa74888b4492a32784675a7
        String url= "http://route.showapi.com/907-3?showapi_appid="+APPID+"&showapi_sign="+API_SIGN;
        String json = getStringJsonByOkHttp(url);
        return json;
    }


    //使用第三方http请求,OHttpClient
    public static String getStringJsonByOkHttp(String urlstr) {

        String json = "";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder
                    .url(urlstr)
                    .tag("tag")
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            boolean successful = response.isSuccessful();
            if (successful) {
                json = response.body().string();//获得字符串,还可以获取字节数组,字节流
            } else {
                json = "网络连接失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
            json = e.toString();
        }
        return json;
    }

}
