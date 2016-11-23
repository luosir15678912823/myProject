package com.qianfeng.mall.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-10-25.
 */
public class NetWorkReceiver extends BroadcastReceiver {

    private static final String TAG = "test";
    public static final String INTENT_ATION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {

            if (listener != null) {
                listener.networkIsAvailable();
                Log.e(TAG, "onReceive: 有可用的网络");
                Toast.makeText(context, "有可用的网络", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "当前没有可用的网络，请检查网络是否可用", Toast.LENGTH_SHORT).show();
        }
    }

    NetWorkListener listener;

    public void setListener(NetWorkListener listener) {
        this.listener = listener;
    }

    public interface NetWorkListener {

        public void networkIsAvailable();
    }
}
