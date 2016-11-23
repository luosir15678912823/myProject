package com.nightly.lovetravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nightly.lovetravel.fragment.MainFragment;
import com.nightly.lovetravel.fragment.MenuFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "test";
    @BindView(R.id.fm)
    FrameLayout fm;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private SlidingMenu slidingMenu;
    private MenuFragment leftMenuFragment;
    private MainFragment mainFragment;
    public static final String SHOW_MENU = "showMenu";
    public static final String HIDE_MENU = "hideMenu";
    public static final String SHOW_MAINFRAGMENT = "showMainFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initLeftSlidingMenu();
        //主页面
        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fm,mainFragment)
                .commit();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 10,sticky = false)
    public void dealEventBusEvent(String event){
        switch (event){
            case SHOW_MENU:
                slidingMenu.showMenu();
                break;
            case HIDE_MENU:
                slidingMenu.toggle();
                break;
            case SHOW_MAINFRAGMENT:
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_left_to_right_enter,R.anim.fragment_left_to_right_exit)
                        .replace(R.id.fm,mainFragment)
                        .commit();
                break;
        }
    }

    //左侧菜单
    private void initLeftSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        leftMenuFragment = new MenuFragment();
        leftMenuFragment.setSlidingMenu(slidingMenu);
        //设置菜单模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setMenu(R.layout.container_slidingmenu);
        //替换布局
        getSupportFragmentManager().beginTransaction().add(R.id.flRoot, leftMenuFragment).commit();
        int width = ((int) (getResources().getDisplayMetrics().widthPixels * (3f / 4)));//获取屏幕宽度
//        Log.e(TAG, "initLeftSlidingMenu: test=="+getResources().getDisplayMetrics().widthPixels+"/"+width );
        slidingMenu.setBehindWidth(width);//宽度
        slidingMenu.setFadeDegree(0.5f);
        slidingMenu.setShadowWidth(1);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//必须要设置，否则无法运行
    }

}
