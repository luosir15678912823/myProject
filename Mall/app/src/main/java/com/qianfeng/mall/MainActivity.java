package com.qianfeng.mall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.mall.adapter.VpFragmentAdapter;
import com.qianfeng.mall.fragment.ClassificationFragment;
import com.qianfeng.mall.fragment.GroupBuyFragment;
import com.qianfeng.mall.fragment.HomeFragment;
import com.qianfeng.mall.fragment.MineFragment;
import com.qianfeng.mall.fragment.PersonalFragment;
import com.qianfeng.mall.fragment.ShoppingCarFragment;
import com.qianfeng.mall.widget.MaterialLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final int SHOW_SHOPPINGCAR = 1;
    public static final int SHOW_PERSONAL_FRAGMENT = 3;
    public static final int SHOW_MAINFRAGMENT=6;

    @Bind(R.id.vp)
    ViewPager vp;
    List<Fragment> fragments;
    @Bind(R.id.rbHome)
    RadioButton rbHome;
    @Bind(R.id.rbClass)
    RadioButton rbClass;
    @Bind(R.id.rbGroupBuy)
    RadioButton rbGroupBuy;
    @Bind(R.id.rbShoppingCar)
    RadioButton rbShoppingCar;
    @Bind(R.id.rbMine)
    RadioButton rbMine;
    @Bind(R.id.rgTab)
    RadioGroup rgTab;
    @Bind(R.id.ML)
    MaterialLayout ML;
    @Bind(R.id.fl)
    FrameLayout fl;
    @Bind(R.id.llRoot)
    LinearLayout llRoot;
    private GroupBuyFragment groupBuyFragment;
    private VpFragmentAdapter adapter;
    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private MineFragment mineFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        fragments = new ArrayList<>();

        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        groupBuyFragment = new GroupBuyFragment();
        shoppingCarFragment = new ShoppingCarFragment();
        mineFragment = new MineFragment();

        fragments.add(homeFragment);
        fragments.add(classificationFragment);
        fragments.add(groupBuyFragment);
        fragments.add(shoppingCarFragment);
        fragments.add(mineFragment);


        adapter = new VpFragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);

        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome:
                        vp.setCurrentItem(0);
                        break;

                    case R.id.rbClass:
                        vp.setCurrentItem(1);
                        break;

                    case R.id.rbGroupBuy:
                        vp.setCurrentItem(2);
                        break;

                    case R.id.rbShoppingCar:
                        vp.setCurrentItem(3);
                        break;

                    case R.id.rbMine:
                        vp.setCurrentItem(4);
                        break;

                }

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < rgTab.getChildCount(); i++) {
                    ((RadioButton) rgTab.getChildAt(i)).setTextColor(Color.parseColor("#707076"));
                    ((RadioButton) rgTab.getChildAt(position)).setChecked(false);

                }
                ((RadioButton) rgTab.getChildAt(position)).setTextColor(Color.parseColor("#eb4f38"));
                ((RadioButton) rgTab.getChildAt(position)).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 10)
    public void eventBusClick(Integer flag) {
        switch (flag) {

            case SHOW_SHOPPINGCAR:
                vp.setCurrentItem(3);
                break;
            case SHOW_PERSONAL_FRAGMENT:
                llRoot.setVisibility(View.GONE);
                fl.setVisibility(View.VISIBLE);
                if (personalFragment==null){
                    personalFragment = new PersonalFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,personalFragment).commit();
                break;
            case SHOW_MAINFRAGMENT:
                llRoot.setVisibility(View.VISIBLE);
                fl.setVisibility(View.GONE);
                break;
        }
    }
}
