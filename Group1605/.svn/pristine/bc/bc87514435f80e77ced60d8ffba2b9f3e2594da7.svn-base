package com.nightly.lovetravel.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightly.lovetravel.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class HotelDetailFragment extends Fragment {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvHotelName)
    TextView tvHotelName;
    @BindView(R.id.tvHotelComment)
    TextView tvHotelComment;
    @BindView(R.id.tvHotelAddress)
    TextView tvHotelAddress;
    @BindView(R.id.tvHotelNearby)
    TextView tvHotelNearby;
    @BindView(R.id.tvHotelType)
    TextView tvHotelType;
    @BindView(R.id.tvHotelintro)
    TextView tvHotelintro;
    @BindView(R.id.tvOneword)
    TextView tvOneword;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.btnMap)
    TextView btnMap;
    private View view;

    private Bundle bundle;
    private Fragment fragment;

    public HotelDetailFragment() {
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_hotel_detail, container, false);
            ButterKnife.bind(this, view);
        }

        String url = bundle.getString("url");
        String[] strings = url.split("_");
        String newUrl = strings[0] + "_400x300_" + strings[2];
        Log.e("test", "newurl: " + newUrl);
        Picasso.with(getContext()).load(newUrl).into(ivPhoto);
        tvHotelName.setText(bundle.getString("hotelName") + "  " + bundle.getString("level"));
        String commentGood = bundle.getString("commentGood");
        String commentTotal = bundle.getString("commentTotal");
        if (!commentGood.equals("") && !commentTotal.equals("")) {
            tvHotelComment.setText("好评：" + commentGood + " " + "评价总数：" + commentTotal);
        } else {
            tvHotelComment.setText("暂无评价");
        }
        tvHotelAddress.setText("地址：" + bundle.getString("address"));
        tvHotelNearby.setText(bundle.getString("nearby"));
        tvHotelType.setText(bundle.getString("hotelType"));
//        tvHotelLevel.setText(bundle.getString("level"));
        tvHotelintro.setText(bundle.getString("intro"));
        tvOneword.setText(bundle.getString("oneWord"));
        tvPrice.setText("价格：" + bundle.getString("price") + "元起");

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String latitude = bundle.getString("latitude");
                    String longitude = bundle.getString("longitude");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("geo:" + latitude + "," + longitude));
                    getActivity().startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "没有安装地图", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tvBack)
    public void onTvBackClick(View v) {
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_left_to_right_enter, R.anim.fragment_left_to_right_exit)
                .replace(R.id.fm, fragment)
                .commit();
    }
}
