package com.nightly.lovetravel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Nightly on 2016/11/10.
 */

public class NetworkReceiver extends BroadcastReceiver {
    private NetWorkChangeListener listener;
    private static final String TAG="test";
    public static final String NETWORK_INTENT_ACTION="android.net.conn.CONNECTIVITY_CHANGE";
    public void setListener(NetWorkChangeListener listener) {
        this.listener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
//        Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
//                +"\n"+"active:"+activeInfo.getTypeName(), Toast.LENGTH_LONG).show();
        Log.e(TAG, "onReceive: activeInfo="+activeInfo);
        if(activeInfo!=null){
            Log.e(TAG, "onReceive: 网络发生变化,listener="+ listener);
            if (listener != null) {
                listener.NetWorkChange(true);
            }
        }else {
            listener.NetWorkChange(false);
        }
    }

    public interface NetWorkChangeListener {
        void NetWorkChange(boolean isAvailable);
    }
}
