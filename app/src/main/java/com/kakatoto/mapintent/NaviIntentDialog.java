package com.kakatoto.mapintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ohyowan on 2017. 6. 17..
 */

public class NaviIntentDialog extends DialogFragment {
    private final String TAG = NaviIntentDialog.class.getSimpleName();
    private String addr;
    private Double lat;
    private Double lng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_navi_fragment, container);
        ButterKnife.bind(this, view);

        this.addr = getArguments().getString("addr");
        this.lat = getArguments().getDouble("lat");
        this.lng = getArguments().getDouble("lng");

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btnKakaoMap, R.id.btnNaverMap, R.id.btnGoogleMap, R.id.btnTMap, R.id.btnKakaoNaviMap})
    public void onKakaoClick(View v) {
        String type = "";
        switch (v.getId()){
            case R.id.btnKakaoMap:
                type = NaviBuilder.KAKAO_MAP;
                break;
            case R.id.btnNaverMap:
                type = NaviBuilder.NAVER_MAP;
                break;
            case R.id.btnGoogleMap:
                type = NaviBuilder.GOOGLE_MAP;
                break;
            case R.id.btnTMap:
                type = NaviBuilder.T_MAP;
                break;
            case R.id.btnKakaoNaviMap:
                type = NaviBuilder.KAKAO_NAVI;
                break;

        }

        NaviBuilder builder = new NaviBuilder.Builder(getActivity())
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(type).builder();
        builder.create();
    }
}
