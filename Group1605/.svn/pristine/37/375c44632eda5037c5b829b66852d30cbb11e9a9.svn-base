package com.nightly.lovetravel;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by qf on 2016/10/11.
 */
public class MyApp extends Application {

    public static Context context=new MyApp();

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }
}
