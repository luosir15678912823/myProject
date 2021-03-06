package com.qianfeng.mall.fragment;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qianfeng.mall.MainActivity;
import com.qianfeng.mall.R;
import com.qianfeng.mall.modle.User;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2016-11-04.
 */

public class PersonalFragment extends Fragment {

    private static final String TAG = "test";
    @Bind(R.id.tvBackii)
    TextView tvBackii;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etRePassword)
    EditText etRePassword;
    @Bind(R.id.btnSavePassword)
    Button btnSavePassword;
    @Bind(R.id.llRoot_ii)
    LinearLayout llRootIi;
    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.tvChangePass)
    TextView tvChangePass;
    @Bind(R.id.ivPic)
    SimpleDraweeView ivPic;
    @Bind(R.id.etSignature)
    EditText etSignature;
    @Bind(R.id.etNick)
    EditText etNick;
    @Bind(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.llRoot_i)
    LinearLayout llRootI;
    private View view;
    private File pic;
    String url = "";
    String path = "";
    final int TAKE_PHOTO = 100;
    final int CHOSE_PHOTO = 200;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_personal, container, false);
        }
        //        第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
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

        ButterKnife.bind(this, view);


        try {
            User user = BmobUser.getCurrentUser(User.class);
            String objectId = user.getObjectId();
            String nickName = user.getNickName();
            String signtrue = user.getSignature();
            String phoneNumber = user.getMobilePhoneNumber();


            etNick.setText(nickName);
            etPhoneNumber.setText(phoneNumber);
            etSignature.setText(signtrue);
            url = user.getPicUrl();
            ivPic.setImageURI(Uri.parse(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    //保存修改信息
    @OnClick(R.id.btnSave)
    public void onBtnSaveClick() {

        updateInfo();
    }

    private void updateInfo() {
        final String signature = etSignature.getText().toString().trim();
        final String nick = etNick.getText().toString().trim();
        final String phoneNumber = etPhoneNumber.getText().toString().trim();
        User user = new User();
        user.setNickName(nick);
        user.setSignature(signature);
        user.setMobilePhoneNumber(phoneNumber);
        user.update(BmobUser.getCurrentUser(User.class).getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().post(true);
                        } else {
                            Toast.makeText(getContext(), "修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "done: " + e.getMessage());
                        }
                    }
                }
        );
        if (path == null) {
            return;
        }
        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.uploadblock(new UploadFileListener() {
                                 @Override
                                 public void done(BmobException e) {
                                     if (e == null) {
                                         url = bmobFile.getFileUrl();
                                         User user = new User();
                                         user.setPicUrl(url);
                                         Log.e(TAG, "onBtnSaveClick: BmobUser.getCurrentUser().getObjectId()=" + BmobUser.getCurrentUser(User.class).getObjectId());
                                         user.update(BmobUser.getCurrentUser(User.class).getObjectId(), new UpdateListener() {
                                             @Override
                                             public void done(BmobException e) {
                                                 if (e == null) {
                                                     Toast.makeText(getContext(), "头像修改成功", Toast.LENGTH_SHORT).show();
                                                     EventBus.getDefault().post(true);
                                                 } else {
                                                     Toast.makeText(getContext(), "头像修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         });

                                     } else {

                                         Toast.makeText(getContext(), "头像修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                     }
                                 }
                             }

        );
    }

    @OnClick(R.id.tvBack)
    public void onTvBackClick() {
        EventBus.getDefault().post(MainActivity.SHOW_MAINFRAGMENT);
    }

    @OnClick(R.id.tvChangePass)
    public void onTvChangePassClick() {
        llRootI.setVisibility(View.GONE);
        llRootIi.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.tvBackii)
    public void onTvBackiiClick() {
        llRootI.setVisibility(View.VISIBLE);
        llRootIi.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnSavePassword)
    public void onBtnSavePasswordClick() {

        String password = etPassword.getText().toString().trim();
        String rePassword = etRePassword.getText().toString().trim();

        if (password.equals("") || rePassword.equals("")) {
            Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(getContext(), "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getContext(), "密码要6位以上", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setPassword(password);
        user.update(BmobUser.getCurrentUser(User.class).getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.ivPic)
    public void onIvPicClick() {

        try {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_selectpic, null, false);
            final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setView(view);
            dialog.show();
            TextView tvNativePic = (TextView) view.findViewById(R.id.tvNativePic);
            TextView tvTakePhoto = (TextView) view.findViewById(R.id.tvTakePhoto);
            tvNativePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
                    intent2.setType("image/*");
                    startActivityForResult(intent2, CHOSE_PHOTO);
                    dialog.dismiss();
                }
            });

            tvTakePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
    //                Toast.makeText(getContext(), "获取现场图片", Toast.LENGTH_SHORT).show();
                    //存储图片的路劲
                    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "test.jpg";

                    //开启照相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                    startActivityForResult(intent, TAKE_PHOTO);
                    dialog.dismiss();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case TAKE_PHOTO:
                takePhoto(requestCode, resultCode, data);
                break;
            case CHOSE_PHOTO:

                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19)
                        handleImageOnkitKat(data);
                    else
                        handleImageBeforkitKat(data);
                }

                break;
        }

    }

    //获取选择本地图片，SDK>=19
    public void handleImageOnkitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(getContext(), uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(".")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contenturi = ContentUris.withAppendedId(Uri.parse("contetnt://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contenturi, null);
                } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                    imagePath = getImagePath(uri, null);
                }
            }
            displayImages(imagePath);
        }
    }

    //选择本地图片 SDK<19
    public void handleImageBeforkitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImages(imagePath);
    }

    //获取图片路径
    public String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //选择本地图片时显示图片
    public void displayImages(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ivPic.setImageBitmap(bitmap);
            path = imagePath;
        } else {
            Toast.makeText(getContext(), "选择图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void takePhoto(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivPic.setImageBitmap(bitmap);
                Log.e(TAG, "onActivityResult: path=" + path);
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                ivPic.setImageBitmap(bitmap);
            }

        } else if (requestCode == RESULT_CANCELED) {
            Toast.makeText(getContext(), "拍照被取消", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "拍照失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
