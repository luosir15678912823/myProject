package com.nightly.lovetravel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nightly.lovetravel.LoginActivity;
import com.nightly.lovetravel.R;
import com.nightly.lovetravel.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.BmobRealTimeData.TAG;

/**
 * Created by Nightly on 2016/10/19.
 */

public class MenuFragment extends Fragment {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.flUser)
    FrameLayout flUser;
    @BindView(R.id.flOperation)
    FrameLayout flOperation;
    private View view;


    private SlidingMenu slidingMenu;
    private LeftMenuBottom leftMenuBottom;
    private LeftMenuTop leftMenuTop;
    private PersonalFragment personalFragment;


    public MenuFragment() {

    }

    public void setSlidingMenu(SlidingMenu slidingMenu) {
        this.slidingMenu = slidingMenu;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_menu, container, false);
            ButterKnife.bind(this, view);
        }
        //顶部菜单
        leftMenuTop = new LeftMenuTop();
        getFragmentManager().beginTransaction().replace(R.id.flUser, leftMenuTop).commit();
        //底部菜单
        leftMenuBottom = new LeftMenuBottom();
        getFragmentManager().beginTransaction().replace(R.id.flOperation, leftMenuBottom).commit();


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

        return view;
    }

    @OnItemClick(R.id.lv)
    public void onLvItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "你点击了第" + position + "项。", Toast.LENGTH_SHORT).show();

        BmobUser bmobUser = BmobUser.getCurrentUser();
        switch (position) {
            case 0:
                if (bmobUser != null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Log.e(TAG, "onLvItemClick: user" + user.getUsername());
                    personalFragment = new PersonalFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fm, personalFragment).commit();
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case 1:
                if (bmobUser!=null){
                    FavoriteFragment favoriteFragment = new FavoriteFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fm, favoriteFragment).commit();
                }else{
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

                break;

            case 3:
                //退出登录
                BmobUser.logOut();
                break;

        }
        if (slidingMenu != null) {
            slidingMenu.toggle();
        }
    }
}
