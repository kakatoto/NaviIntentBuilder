package com.kakatoto.mapintent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private String addr;
    private double lat;
    private double lng;

    @BindView(R.id.txtAddr)
    TextView txtAddrView;
    @BindView(R.id.txtGeo)
    TextView txtGeoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getHashKey();
        inits();
    }

    public void inits() {
        this.addr = "카카오 판교오피스";
        this.lat = 37.40205604363057;
        this.lng = 127.10821222694533;

        txtAddrView.setText(addr);
        txtGeoView.setText(lat + ", " + lng);
    }


    @OnClick(R.id.btnNaviPopup)
    public void onGoogleMapIntent() {
        NaviIntentDialog naviIntentDialog = new NaviIntentDialog();
        Bundle bundle = new Bundle();
        FragmentManager fm = getSupportFragmentManager();
        bundle.putString("addr", addr);
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        naviIntentDialog.setArguments(bundle);
        naviIntentDialog.show(fm, "NaviIntentDialog");
    }



    public void getHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
