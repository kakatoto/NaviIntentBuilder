package com.kakatoto.mapintent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;
import com.skp.Tmap.TMapTapi;

import java.util.List;

/**
 * Created by darong on 2017. 6. 16..
 */

public class NaviBuilder {
    private final String TAG = NaviBuilder.class.getSimpleName();
    public final static String GOOGLE_MAP = "com.google.android.apps.maps";
    public final static String NAVER_MAP = "com.nhn.android.nmap";
    public final static String KAKAO_MAP = "net.daum.android.map";
    public final static String KAKAO_NAVI = "kakao_navi";
    public final static String T_MAP = "com.skt.tmap.ku";

    private Context context;
    private String addr;
    private double lat;
    private double lng;
    private String type;

    private NaviBuilder(Builder builder) {
        this.context = builder.context;
        this.addr = builder.addr;
        this.lat = builder.lat;
        this.lng = builder.lng;
        this.type = builder.type;
    }

    public static class Builder {
        private Context context;
        private String addr;
        private Double lat;
        private Double lng;
        private String type;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setAddr(String addr) {
            this.addr = addr;
            return this;
        }

        public Builder setLatLng(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public NaviBuilder builder() {
            return new NaviBuilder(this);
        }
    }

    public void create() {
        switch (type) {
            case GOOGLE_MAP:
                intentGoogleMap();
                break;

            case NAVER_MAP:
                intentNaverMap();
                break;

            case KAKAO_MAP:
                intentKakaoMap();
                break;

            case KAKAO_NAVI:
                intentKakaoNevi();
                break;

            case T_MAP:
                intentTmap();
                break;
        }
    }


    private void intentGoogleMap() {
        if (isAppInstall(GOOGLE_MAP)) {

            StringBuilder sb = new StringBuilder("geo:").
                    append(lat + "," + lng).
                    append("?q=").append(Uri.encode(addr));

            Uri uri = Uri.parse(sb.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(GOOGLE_MAP);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else {
            Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
            marketLaunch.setData(Uri.parse("market://details?id=" + GOOGLE_MAP));
            context.startActivity(marketLaunch);
        }
    }

    private void intentKakaoMap() {
        if (isAppInstall(KAKAO_MAP)) {
            StringBuilder sb = new StringBuilder("daummaps://look").
                    append("?p=").append(lat + "," + lng);

            Uri uri = Uri.parse(sb.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(KAKAO_MAP);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else {
            Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
            marketLaunch.setData(Uri.parse("market://details?id=" + KAKAO_MAP));
            context.startActivity(marketLaunch);
        }
    }

    private void intentNaverMap() {
        if (isAppInstall(NAVER_MAP)) {
            StringBuilder sb = new StringBuilder("geo:").
                    append((float)lat + "," + (float)lng).
                    append("?q=").append(Uri.encode(addr));
            Uri uri = Uri.parse(sb.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(NAVER_MAP);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        } else {
            Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
            marketLaunch.setData(Uri.parse("market://details?id=" + NAVER_MAP));
            context.startActivity(marketLaunch);
        }
    }

    private void intentKakaoNevi() {
        Location destination = Location.newBuilder(addr, lat, lng).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviService.navigate(context, builder.build());

    }

    private void intentTmap() {
        if (isAppInstall(T_MAP)) {
            final TMapTapi tMapTapi = new TMapTapi(context);
            tMapTapi.setSKPMapAuthentication(context.getString(R.string.sk_app_key));
            tMapTapi.invokeRoute(addr, (float) lat, (float) lng);

        }else {
            Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
            marketLaunch.setData(Uri.parse("market://details?id=" + T_MAP));
            context.startActivity(marketLaunch);
        }
    }

    private Boolean isAppInstall(String pkname) {
        boolean isExist = false;
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> mApps;
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = packageManager.queryIntentActivities(mIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if (mApps.get(i).activityInfo.packageName.startsWith(pkname)) {
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }

        return isExist;
    }

}
