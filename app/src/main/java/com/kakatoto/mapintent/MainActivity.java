package com.kakatoto.mapintent;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private String addr;
    private Double lat;
    private Double lng;

    @BindView(R.id.txtAddr)
    TextView txtAddrView;
    @BindView(R.id.txtGeo)
    TextView txtGeoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inits();
    }

    public void inits() {
        this.addr = "카카오 판교오피스";
        this.lat = 127.10821222694533;
        this.lng = 37.40205604363057;

        txtAddrView.setText(addr);
        txtGeoView.setText(lat + ", " + lng);
    }


    @OnClick(R.id.btnGoogleMap)
    public void onGoogleMapIntent() {
        NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.GOOGLE_MAP).builder();
        builder.create();
    }

    @OnClick(R.id.btnDaumMap)
    public void onDaumMapIntent() {
        NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.KAKAO_MAP).builder();
        builder.create();
    }

    @OnClick(R.id.btnNaverMap)
    public void onNaverMapIntent() {
        NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.NAVER_MAP).builder();
        builder.create();
    }

    @OnClick(R.id.btnKakaoNeviMap)
    public void onKakaoNeviIntent() {
        NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.KAKAO_NAVI).builder();
        builder.create();
    }

    @OnClick(R.id.btnTMap)
    public void onTMapIntent() {
        NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.T_MAP).builder();
        builder.create();
    }
}
