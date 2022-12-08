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

        // saferecorder 로그인 버튼 클릭시 로그인 페이지로 이동
        btn_saferecorder_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(OpenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }

}
