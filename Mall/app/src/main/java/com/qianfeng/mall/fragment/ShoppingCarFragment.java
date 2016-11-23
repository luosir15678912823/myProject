package com.qianfeng.mall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mall.R;
import com.qianfeng.mall.adapter.ShoppingCarAdapter;
import com.qianfeng.mall.modle.BmobCarBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by qf on 2016/10/20.
 */
public class ShoppingCarFragment extends Fragment {

     static final String TAG = "test";
    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.lvShoppingCar)
    ListView lvShoppingCar;
    private View view;
    private ShoppingCarAdapter adapter;
    List<BmobCarBean> data=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_shopping, container, false);
        }
        initBmob();
        initData();
        ButterKnife.bind(this, view);
        adapter = new ShoppingCarAdapter(getContext(),data);
        lvShoppingCar.setAdapter(adapter);
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

    //初始化数据，从云端数据库查询数据
    private void initData() {
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser==null){
            return;
        }
        String userId = currentUser.getObjectId();
        BmobQuery<BmobCarBean> query=new BmobQuery<>();
        query.addWhereEqualTo("userId",userId);
        query.findObjects(new FindListener<BmobCarBean>() {
            @Override
            public void done(List<BmobCarBean> list, BmobException e) {

                if (e==null){
                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "data="+data.size(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 10)
    public void updateInfo(Boolean flag){
        if (flag){
            initData();
            Toast.makeText(getContext(), "加入购物车", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
