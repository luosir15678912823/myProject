package com.nightly.lovetravel.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightly.lovetravel.MainActivity;
import com.nightly.lovetravel.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nightly on 2016/10/22.
 */

public class LeftMenuBottom extends Fragment {
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    @BindView(R.id.tvExit)
    TextView tvExit;
    private View view;
    private SettingFragment settingFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_left_bottom, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    /**
     * 退出按钮
     *
     * @param view
     */
    @OnClick(R.id.tvExit)
    public void onTvExitClick(View view) {
        AlertDialog.Builder builder = new AlertDialog
                .Builder(getContext());
        builder.setMessage("是否要退出爱出门？")
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .create().show();
    }

    @OnClick(R.id.tvSetting)
    public void onTvSetting(View view) {
        EventBus.getDefault().post(MainActivity.HIDE_MENU);
        settingFragment = new SettingFragment();
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_to_left_enter, R.anim.fragment_left_to_right_exit)
                .replace(R.id.fm, settingFragment)
                .commit();
    }
}
