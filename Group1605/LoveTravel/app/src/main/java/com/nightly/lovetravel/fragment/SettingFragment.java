package com.nightly.lovetravel.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nightly.lovetravel.MainActivity;
import com.nightly.lovetravel.R;
import com.nightly.lovetravel.util.ThreadUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nightly on 2016/11/4.
 */

public class SettingFragment extends Fragment {
    @BindView(R.id.tvExitSetting)
    TextView tvExitSetting;
    @BindView(R.id.tvCleanCache)
    TextView tvCleanCache;
    private View view;
    private final static int MSG_DELETE_CACHE =1;
    private final static String MSG_DELETE_CACHE_SUCCESS ="success";
    private final static String MSG_DELETE_CACHE_FAIL ="fail";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_DELETE_CACHE:
                    Toast.makeText(getContext(), MSG_DELETE_CACHE_SUCCESS.equals((String) msg.obj)?"缓存清理成功！":"缓存清理失败，请重试...", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_setting, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    /**
     * 清除缓存
     * @param view
     */
    @OnClick(R.id.tvCleanCache)
    public void onTvCleanCacheClick(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否要清除缓存？")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThreadUtil.execute(new Runnable() {
                            @Override
                            public void run() {
                                File cacheDir = getContext().getCacheDir();
                                cacheDir.delete();
                                if(cacheDir.exists()){
                                    sendMessage(MSG_DELETE_CACHE,"fail");
                                }else {
                                    sendMessage(MSG_DELETE_CACHE,"success");
                                }
                            }
                        });

                    }
                })
                .create()
                .show();

    }

    /**
     * 发送消息
     * @param what
     * @param object
     */
    private void sendMessage(int what,Object object) {
        Message message = handler.obtainMessage();
        message.what=what;
        message.obj=object;
        handler.sendMessage(message);
    }

    /**
     * 退出应用
     * @param view
     */
    @OnClick(R.id.tvExitSetting)
    public void onTvExitSettingClick(View view){
        EventBus.getDefault().post(MainActivity.SHOW_MAINFRAGMENT);
    }
}
