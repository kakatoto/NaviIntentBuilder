
# NaviIntentBuilder

앱 내의 주소, 위경도를 활용하여 지도앱 호출(구글맵, 카카오지도, 네이버지도, 카카오내비, 티맵)

### Setting


##### Gradle
카카오내비 및 티맵의 경우 sdk 필요

    repositories {
        maven { url 'http://devrepo.kakao.com:8088/nexus/content/groups/public/' }
    }
    
    dependencies {
      compile 'com.kakao.sdk:kakaonavi:1.3.0'
      compile files('libs/com.skp.Tmap_1.0.47.jar')
    }

##### Permission
INTERNET 퍼미션 추가 

    <uses-permission android:name="android.permission.INTERNET" />

# How to use
NaviBuilder에 선언된 타입사용하여 호출할 앱 선택, 주소, 위경도 입력

    public final static String GOOGLE_MAP = "com.google.android.apps.maps";
    public final static String NAVER_MAP = "com.nhn.android.nmap";
    public final static String KAKAO_MAP = "net.daum.android.map";
    public final static String KAKAO_NAVI = "kakao_navi";
    public final static String T_MAP = "com.skt.tmap.ku";


     NaviBuilder builder = new NaviBuilder.Builder(this)
                .setAddr(addr)
                .setLatLng(lat, lng)
                .setType(NaviBuilder.GOOGLE_MAP).builder();
        builder.create();
        

##### ScreenShot
![alt text](https://github.com/kakatoto/NaviIntentBuilder/blob/master/KakaoTalk_Photo_2017-06-18-16-32-41_51.gif)
