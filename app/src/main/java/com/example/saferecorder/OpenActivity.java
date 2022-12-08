package com.example.saferecorder;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.security.MessageDigest;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import java.util.Arrays;




public class OpenActivity extends AppCompatActivity {

    private View btn_kakao_login;
    private View btn_saferecorder_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        btn_kakao_login = findViewById(R.id.lg_kakao_btn);
        btn_saferecorder_login = findViewById(R.id.lg_saferecorder_btn);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>(){
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null) {    // 로그인 성공시 처리
                    updateKakaoLogin();
                }
                if(throwable != null) {     // 로그인 실패시

                }

                return null;
            }

        };
        btn_kakao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(OpenActivity.this)) {  // 해당 기기에 카톡이 설치되어 있는 경우
                    UserApiClient.getInstance().loginWithKakaoTalk(OpenActivity.this, callback);
                } else{    //해당 기기 카톡 설치 X시 카톡 웹페이지로 로그인
                    UserApiClient.getInstance().loginWithKakaoAccount(OpenActivity.this, callback);
                }
            }
        });

        // saferecorder 로그인 버튼 클릭시 로그인 페이지로 이동
        btn_saferecorder_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(OpenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        updateKakaoLogin();
        getAppKeyHash();

    }
    // 로그인이 되어있는지 안되어있는지 확인 후 button 처리
    private void updateKakaoLogin() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null) {

                    Intent intent =  new Intent(OpenActivity.this, ServiceActivity.class);
                    startActivity(intent);
                } else {

                }

                return null;
            }
        });
    }
    /* 카카오 로그인 시 필요한 해시키를 얻는 메소드 */
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }


}
