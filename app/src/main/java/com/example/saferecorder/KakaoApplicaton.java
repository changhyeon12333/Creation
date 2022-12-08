package com.example.saferecorder;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplicaton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "6ea79806e036bb0e19b63fce34628eb7");
    }
}
