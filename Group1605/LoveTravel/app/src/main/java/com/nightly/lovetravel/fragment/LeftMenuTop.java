package com.nightly.lovetravel.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.User;
import com.nightly.lovetravel.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Nightly on 2016/10/22.
 */

public class LeftMenuTop extends Fragment {
    @BindView(R.id.imageView)
    SimpleDraweeView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ivQRCode)
    ImageView ivQRCode;
    @BindView(R.id.tvSignature)
    TextView tvSignature;
    private View view;
    final String TAG = "test";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_left_top, container, false);
        }

        ButterKnife.bind(this, view);

        //        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(getContext())
                //设置appkey
                .setApplicationId("2d254141444030d2b8b7e5b1eb36b7ef")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(10)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        EventBus.getDefault().register(this);

        showInfo();

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateInfo(Boolean flag) {
        if (flag)
            showInfo();
    }

    private void showInfo() {
        try {
            User user = BmobUser.getCurrentUser(User.class);
            textView.setText(user.getNickName());
            tvSignature.setText(user.getSigntrue());
            imageView.setImageURI(Uri.parse(user.getPic()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //扫描二维码
    @OnClick(R.id.ivQRCode)
    public void onIvQRCodeClick(View view) {
        startActivityForResult(new Intent(getContext(), CaptureActivity.class), 100);
        Log.e(TAG, "onIvQRCodeClick: ivQRCode");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            Toast.makeText(getActivity(), "result=" + result, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onActivityResult: url=" + result);

            if (!result.contains("http")) {
                result = "http://" + result;
            }

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(result));
            startActivity(intent);

        }

    }

}
