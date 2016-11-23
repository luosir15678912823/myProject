package com.nightly.lovetravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nightly.lovetravel.bean.User;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "test";
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.ivQQ)
    ImageView ivQQ;
    @BindView(R.id.ivWeChat)
    ImageView ivWeChat;
    @BindView(R.id.ivWeiBo)
    ImageView ivWeiBo;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.tvBack)
    TextView tvBack;
    private boolean ssoLoginSucceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(this)
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

        //初始化第三方登陆
        ShareSDK.initSDK(this);

    }

    //点击注册，跳转到注册页面
    @OnClick(R.id.tvRegister)
    public void onTvRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    //用Bmob的账号登录
    @OnClick(R.id.btnLogin)
    public void onBtnLoginClick() {
        final String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.equals("") || password.equals("")) {
            Toast.makeText(this, "数据不能为空，请填写完整", Toast.LENGTH_SHORT).show();
            return;
        }

        login(userName, password);
    }

    //登陆
    private void login(String userName, String password) {

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {

                if (e == null) {
                    finish();
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(true);
                } else {
                    Toast.makeText(LoginActivity.this, "登陆失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    public void ssoLogin(View view) {
//        ssoLogin(ShareSDK.getPlatform(QQ.NAME));
//    }

    //QQ登录
    @OnClick(R.id.ivQQ)
    public void qqLogin() {
        ssoLogin(ShareSDK.getPlatform(QQ.NAME));
    }

    //微信登录
    @OnClick(R.id.ivWeChat)
    public void weChatLogin() {
        ssoLogin(ShareSDK.getPlatform(Wechat.NAME));
    }

    //微博登录
    @OnClick(R.id.ivWeiBo)
    public void weiBoLogin() {
        ssoLogin(ShareSDK.getPlatform(SinaWeibo.NAME));
    }


    /**
     * 使用第三方平台登录
     *
     * @param platform QQ、微博、微信等第三方平台
     */
    public void ssoLogin(Platform platform) {

        //如果平台为空
        if (platform == null) {
            return;
        }

        // true使用SSO授权，false不使用SSO授权
        platform.SSOSetting(true);

        if (platform.isAuthValid() == true) {

            Log.e(TAG, "ssoLogin:============== ");
            //如果用户已经授权使用该平台账号登陆，则拿取用户信息进行显示
            String userId = platform.getDb().getUserId();
            if (userId != null) {
                String userName = platform.getDb().getUserName();
//                ((TextView) findViewById(R.id.tvName)).setText("你好，" + userName);
                Log.e(TAG, "ssoLogin: userName/userId=" + userName + "/" + userId);
//                    login(userId,platform.getName());//相当于在自家服务器启动登录
                //保存数据到自家服务器
                saveDataToOurServer(userId, userName);
                ssoLoginSucceed = true;

                //登录自家服务器
                loginOurServer(userId);
                return;
            }
        } else {
            //如果用户未授权，则引导用户进行授权
            platform.authorize();
            platform.showUser(null);//获取用户信息
        }

        //用户授权成功与否的回调
        platform.setPlatformActionListener(new PlatformActionListener() {

            /**
             * 授权完成时的回调
             * @param platform 进行授权的平台
             * @param i 代表“Action”的类型，8=平台授权 1=
             * @param hashMap 操作成功返回的具体数据
             */
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e(TAG, "onComplete: " + platform.getName() + ",i=" + i + "map=" + hashMap);
                if (i == 8 && !ssoLoginSucceed) {
                    ssoLogin(platform);
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d(TAG, "onError() called with: " + "platform = [" + platform + "], i = [" + i + "], throwable = [" + throwable + "]");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d(TAG, "onCancel() called with: " + "platform = [" + platform + "], i = [" + i + "]");
            }
        });

    }

    //使用第三方登录时将数据保存到服务器数据库
    private void saveDataToOurServer(String userId, String userName) {

        User user = new User();
        user.setUsername(userId);
        user.setPassword(userId);
        user.setNickName(userName);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {


            }
        });

    }

    //登录自家服务器
    private void loginOurServer(String userId) {

        User user = new User();
        user.setUsername(userId);
        user.setPassword(userId);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {

                if (e == null) {
                    finish();
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(true);
                } else {
                    Toast.makeText(LoginActivity.this, "登陆失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void removePlatformAuth(final View view) {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        if (!platform.isAuthValid()) {
            Toast.makeText(this, "未发现授权", Toast.LENGTH_SHORT).show();
//            ((TextView) findViewById(R.id.tvName)).setText("第三方登录信息");
            return;
        }

        platform.removeAccount(true);//移除授权
//        ((TextView) findViewById(R.id.tvName)).setText("第三方登录信息");

        /**
         * 移除授权时并不会回调
         */
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e(TAG, "onComplete: " + platform.getName() + ",i=" + i + "map=" + hashMap);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d(TAG, "onError() called with: " + "platform = [" + platform + "], i = [" + i + "], throwable = [" + throwable + "]");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d(TAG, "onCancel() called with: " + "platform = [" + platform + "], i = [" + i + "]");
            }
        });
    }

    @OnClick(R.id.tvBack)
    public void ontvBackClick(){
        finish();
    }
}
