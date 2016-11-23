package com.qianfeng.mall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qianfeng.mall.modle.BmobCarBean;
import com.qianfeng.mall.modle.GroupBuyToday;
import com.qianfeng.mall.modle.QueryBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "test";
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.weChat)
    ImageView weChat;
    @Bind(R.id.QQ)
    ImageView QQ;
    @Bind(R.id.tvSummary)
    TextView tvSummary;
    @Bind(R.id.tvLookEvaluation)
    TextView tvLookEvaluation;
    @Bind(R.id.btnAddShoppingCar)
    Button btnAddShoppingCar;
    @Bind(R.id.btnBuy)
    Button btnBuy;
    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.tvCurrentPrice)
    TextView tvCurrentPrice;
    @Bind(R.id.tvMarketPrice)
    TextView tvMarketPrice;
    @Bind(R.id.tvShowCount)
    TextView tvShowCount;
    @Bind(R.id.tvActivityTime)
    TextView tvActivityTime;

    public static final int SIMPLE_FLAG = 1;
    public static final int COMPLEX_FLAG = 2;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tvLine)
    TextView tvLine;
    @Bind(R.id.tvTime)
    TextView tvTime;
    private QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean contentlistBean;
    private GroupBuyToday.ShowapiResBodyBean.ListBean listBean;
    private int flag;
    List<BmobCarBean> data = new ArrayList<>();
    String commdityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initBmob();
        initData();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        flag = bundle.getInt("flag");

        if (flag == SIMPLE_FLAG) {
            contentlistBean = (QueryBean.ShowapiResBodyBean.PageBeanBean.ContentlistBean) bundle.getSerializable("bean");
            String name = contentlistBean.getName();
            commdityName=name;
            tvSummary.setText(name);
            tvCurrentPrice.setText("现价:" + contentlistBean.getPrice());
            Glide.with(this).load(contentlistBean.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);

            tvActivityTime.setVisibility(View.GONE);
            tvMarketPrice.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
            tvLine.setVisibility(View.GONE);
            tvShowCount.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        } else if (flag == COMPLEX_FLAG) {

            listBean = (GroupBuyToday.ShowapiResBodyBean.ListBean) bundle.getSerializable("bean");
            String title = listBean.getTitle();
            commdityName=title;
            tvSummary.setText(title);
            tvCurrentPrice.setText("现价:￥" + listBean.getPrice());
            Glide.with(this).load(listBean.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);

            tvActivityTime.setText("" + listBean.getStart_time() + "~" + listBean.getEnd_time());
            tvMarketPrice.setText("￥" + listBean.getMarket_price());
            tvShowCount.setText("仅剩：" + listBean.getShow_count() + "件");
        }
    }

    private void initBmob() {
        //        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(this)
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

    @OnClick(R.id.tvBack)
    public void onTvBackClick() {
        finish();
    }

    @OnClick(R.id.btnAddShoppingCar)
    public void onBtnAddShoppingCar() {
        String imgUrl = "";
        String name = "";
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            if (flag == SIMPLE_FLAG) {
                imgUrl = contentlistBean.getImg();
                name = contentlistBean.getName();
            } else if (flag == COMPLEX_FLAG) {
                name = listBean.getTitle();
                imgUrl = listBean.getImg();
            }

            String objectId = currentUser.getObjectId();
            //先判断是否已经加入过购物车，如果已经加入过，则不再加入否则加入
            for (BmobCarBean bmobCarBean : data) {
                if (name.equals(bmobCarBean.getName())){
                    Toast.makeText(this, "该商品已经加入到购物车，无需再次添加", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            BmobCarBean bmobCarBean = new BmobCarBean(objectId, name, imgUrl);
            bmobCarBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(DetailActivity.this, "加入购物车成功！", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(true);
                        initData();
                    } else {
                        Toast.makeText(DetailActivity.this, "加入购物城失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    //初始化数据，从云端数据库查询数据
    private void initData() {
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser == null) {
            return;
        }
        String userId = currentUser.getObjectId();
        BmobQuery<BmobCarBean> query = new BmobQuery<>();
        query.addWhereEqualTo("userId", userId);
        query.findObjects(new FindListener<BmobCarBean>() {
            @Override
            public void done(List<BmobCarBean> list, BmobException e) {

                if (e == null) {
                    data.addAll(list);
                }
                Log.e(TAG, "done: data==" + data);
            }
        });
    }

    @OnClick(R.id.tvLookEvaluation)
    public void onTvLookEvaluation(){
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra("commodityName",commdityName);
        startActivity(intent);
    }

}
