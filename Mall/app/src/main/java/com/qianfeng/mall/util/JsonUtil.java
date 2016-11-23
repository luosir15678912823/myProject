package com.qianfeng.mall.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qianfeng.mall.modle.Advertisment;
import com.qianfeng.mall.modle.Classification;
import com.qianfeng.mall.modle.GroupBuyToday;
import com.qianfeng.mall.modle.QueryBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qf on 2016/10/8.
 */
public class JsonUtil {


    private static final String TAG = "test";

    public static GroupBuyToday parseGroupBuyToday(String json) {

        GroupBuyToday groupBuyToday = null;
        try {
            groupBuyToday = new Gson().fromJson(json, GroupBuyToday.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return groupBuyToday;
    }

    public static Map<String, List<Classification>> parseClassification(String json) {
        Map<String, List<Classification>> map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
            JSONArray typeList = showapi_res_body.getJSONArray("typeList");
            for (int i = 0; i < typeList.length(); i++) {
                List<Classification> list = new ArrayList<>();
                JSONObject typeListJSONObject = typeList.getJSONObject(i);
                String nameStr = typeListJSONObject.getString("name").trim();
                Log.e(TAG, "parseClassification: nameStr=" + nameStr);
                JSONArray childList = typeListJSONObject.getJSONArray("childList");
                for (int j = 0; j < childList.length(); j++) {
                    JSONObject childListJSONObject = childList.getJSONObject(j);
                    JSONArray childs = childListJSONObject.getJSONArray("childList");
                    for (int k = 0; k < childs.length(); k++) {

                        JSONObject object = childs.getJSONObject(k);
                        String cid = object.getString("cid");
                        String name = object.getString("name");
                        Classification classification = new Classification(cid, name);
                        list.add(classification);
                    }
                }

                Log.e(TAG, "parseClassification: list=" + list);
                map.put(nameStr, list);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static QueryBean parseQueryBean(String json) {
        QueryBean queryBean = null;
        try {
            Gson gson = new Gson();
            queryBean = gson.fromJson(json, QueryBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return queryBean;

    }

    public static Advertisment parseAdvertisment(String json) {

        Advertisment advertisment = null;
        try {
            Gson gson = new Gson();
            advertisment = gson.fromJson(json, Advertisment.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return advertisment;
    }

}
