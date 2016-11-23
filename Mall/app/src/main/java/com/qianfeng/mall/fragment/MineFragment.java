package com.qianfeng.mall.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qianfeng.mall.CommentActivity;
import com.qianfeng.mall.LoginActivity;
import com.qianfeng.mall.MainActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.User;
import com.qianfeng.mall.widget.MyListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;

/**
 * Created by qf on 2016/10/20.
 */
public class MineFragment extends Fragment {


    @Bind(R.id.sdvPic)
    SimpleDraweeView sdvPic;
    @Bind(R.id.tvNickName)
    TextView tvNickName;
    @Bind(R.id.tvSignature)
    TextView tvSignature;
    @Bind(R.id.ll)
    RelativeLayout ll;
    @Bind(R.id.tvLogin)
    TextView tvLogin;
    @Bind(R.id.tvCancle)
    TextView tvCancle;
    @Bind(R.id.lv)
    MyListView lv;
    @Bind(R.id.tvSetting)
    TextView tvSetting;
    @Bind(R.id.tvExit)
    TextView tvExit;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        }

        ButterKnife.bind(this, view);
        initBmob();
        showInfo();

        return view;
    }

    private void initBmob() {
        //        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(getContext())
                //设置appkey
                .setApplicationId("b2bdb4d7189b0f65c5f58895e3443cb3")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(10)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }

    private void showInfo() {
        try {
            User user = BmobUser.getCurrentUser(User.class);
            tvNickName.setText(user.getNickName());
            tvSignature.setText(user.getSignature());
            sdvPic.setImageURI(Uri.parse(user.getPicUrl()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tvLogin)
    public void onTvLogin() {
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(getContext(), LoginActivity.class));
        } else {

            Toast.makeText(getContext(), "已经登录了，不需再次登录", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tvCancle)
    public void onTvCancle() {
        BmobUser.logOut();
    }

    @OnItemClick(R.id.lv)
    public void onLvItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {

            case 0:

                break;

            case 1:
                EventBus.getDefault().post(MainActivity.SHOW_SHOPPINGCAR);
                break;

            case 2:
                startActivity(new Intent(getContext(), CommentActivity.class));
                break;
            case 3:
                EventBus.getDefault().post(MainActivity.SHOW_PERSONAL_FRAGMENT);
                break;
        }
    }

    @OnClick(R.id.tvExit)
    public void onTvExit() {
        System.exit(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 20)
    public void updateInfo(Boolean flag) {
        if (flag)
            showInfo();
    }

}
