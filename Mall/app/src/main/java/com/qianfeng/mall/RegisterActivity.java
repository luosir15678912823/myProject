package com.qianfeng.mall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mall.modle.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {


    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etRePassword)
    EditText etRePassword;
    @Bind(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @Bind(R.id.tvLogin)
    TextView tvLogin;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    @Bind(R.id.activity_register)
    LinearLayout activityRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
//        Bmob.initialize(this, "Your Application ID");

//        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
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

    //点击注册，提交数据到后端云服务器
    @OnClick(R.id.btnRegister)
    public void onBtnRegisterClick(View view) {
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String rePaaword = etRePassword.getText().toString().trim();

        if (userName.equals("") || password.equals("") || rePaaword.equals("")) {
            Toast.makeText(this, "数据不能为空，请填写完整！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePaaword)) {
            Toast.makeText(this, "两次输入的密码不一致！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "密码不能少于6位数", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!userName.matches("[0-9a-zA-Z]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}")) {
            Toast.makeText(RegisterActivity.this, "用户名格式不正确，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(userName);
//        user.setEmailVerified(true);
        user.setMobilePhoneNumber(phoneNumber);
        user.setMobilePhoneNumberVerified(true);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {

                if (e == null) {
                    tvLogin.setText("注册成功，马上去登录");
//                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(RegisterActivity.this, "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    tvLogin.setText("注册失败:"+e.getMessage());
                }

            }
        });

    }

    //返回
    @OnClick({R.id.tvBack, R.id.tvLogin})
    public void onTvbackClick() {
        finish();
    }

}
