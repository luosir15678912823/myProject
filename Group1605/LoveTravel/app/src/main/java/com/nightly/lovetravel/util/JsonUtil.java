package com.nightly.lovetravel.util;

import com.google.gson.Gson;
import com.nightly.lovetravel.bean.AttractionsBean;
import com.nightly.lovetravel.bean.CityBean;
import com.nightly.lovetravel.bean.CityHotelBean;
import com.nightly.lovetravel.bean.CountryBean;
import com.nightly.lovetravel.bean.Location;
import com.nightly.lovetravel.bean.ProvinceBean;
import com.nightly.lovetravel.bean.SectionBean;
import com.nightly.lovetravel.bean.WeatherBean;

/**
 * Created by Nightly on 2016/10/8.
 */

public class JsonUtil {
    public static AttractionsBean parseAttractionsBean(String json) {
        AttractionsBean attractionsBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            attractionsBean = new Gson().fromJson(json, AttractionsBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            attractionsBean = null;
            e.printStackTrace();
        }
        return attractionsBean;
    }

    public static ProvinceBean parseToProvinceBean(String json) {
        ProvinceBean provinceBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            provinceBean = new Gson().fromJson(json, ProvinceBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            provinceBean = null;
            e.printStackTrace();
        }
        return provinceBean;
    }

    public static CityBean parseToCityBean(String json) {
        CityBean cityBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            cityBean = new Gson().fromJson(json, CityBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            cityBean = null;
            e.printStackTrace();
        }
        return cityBean;
    }

    public static CountryBean parseToCountryBean(String json) {
        CountryBean countryBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            countryBean = new Gson().fromJson(json, CountryBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            countryBean = null;
            e.printStackTrace();
        }
        return countryBean;
    }

    public static Location parseToLocationBean(String json) {
        Location locationBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            locationBean = new Gson().fromJson(json, Location.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            locationBean = null;
            e.printStackTrace();
        }
        return locationBean;
    }

    public static CityHotelBean parseToCityHotelBean(String json) {
        CityHotelBean cityHotelBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            cityHotelBean = new Gson().fromJson(json, CityHotelBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            cityHotelBean = null;
            e.printStackTrace();
        }
        return cityHotelBean;
    }
    public static SectionBean parseToSectionBean(String json) {
        SectionBean sectionBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            sectionBean = new Gson().fromJson(json, SectionBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            sectionBean = null;
            e.printStackTrace();
        }
        return sectionBean;
    }
    public static WeatherBean parseToWeatherBean(String json) {
        WeatherBean weatherBean;
        try {
            //在某些情况下，服务器可能返回的不是标准的Bean（服务器出错、连接失败等）
            weatherBean = new Gson().fromJson(json, WeatherBean.class);
        } catch (Exception e) {
            //当解析出错的时候，将Bean设为null
            weatherBean = null;
            e.printStackTrace();
        }
        return weatherBean;
    }

}
