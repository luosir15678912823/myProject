package com.qianfeng.mall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mall.adapter.CommentAdapter;
import com.qianfeng.mall.modle.Comment;
import com.qianfeng.mall.modle.User;
import com.qianfeng.mall.widget.MyListView;

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

public class CommentActivity extends AppCompatActivity {

    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.tvComment)
    TextView tvComment;
    @Bind(R.id.lvComment)
    MyListView lvComment;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.btnPublish)
    Button btnPublish;
    @Bind(R.id.llRoot)
    LinearLayout llRoot;
    private CommentAdapter adapter;
    private String commodityName;
    List<Comment> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initBmob();
        commodityName = getIntent().getStringExtra("commodityName");

        initData();

        adapter = new CommentAdapter(this,data);
        lvComment.setAdapter(adapter);

    }

    private void initData() {
        BmobQuery<Comment> query=new BmobQuery<>();
        query.addWhereEqualTo("commodityName",commodityName);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {

                if (e==null){
                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
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
    public void onTvBack() {
        finish();
    }

    @OnClick(R.id.tvComment)
    public void onTvComment() {
        lvComment.setSelection(data.size()-1);
        Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnPublish)
    public void onBtnPublish(){

        User user = BmobUser.getCurrentUser(User.class);
        String userId = user.getObjectId();
        String nickName = user.getNickName();
        String picUrl = user.getPicUrl();
        String commentContent = etComment.getText().toString().trim();
        if (etComment.length()<=0){
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }

        final Comment comment=new Comment(userId,nickName,picUrl,commodityName,commentContent);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Toast.makeText(CommentActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                    data.add(comment);
                    adapter.notifyDataSetChanged();
                    etComment.setText("");
                }
            }
        });
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void UpdateInfo(Boolean flag){
//        if (flag){
//            User user = BmobUser.getCurrentUser(User.class);
//            String userId = user.getObjectId();
//            String picUrl = user.getPicUrl();
//            String nickName = user.getNickName();
//            Comment comment=new Comment();
//            comment.setPicUrl(picUrl);
//            comment.setNickName(nickName);
//            comment.
//        }
//    }
}
