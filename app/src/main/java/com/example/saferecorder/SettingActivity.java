package com.example.saferecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private Button btn_close;
    private Button btn_priv;
    private Button btn_count;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_close=findViewById(R.id.app_down_btn);
        btn_priv=findViewById(R.id.priv_cng_btn);
        btn_count=findViewById(R.id.count_check_btn);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 개인정보변경 클릭시 프레그먼트 변환
        btn_priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMypage(0);
            }
        });
        // BL,AL값 확인 클릭시 프레그먼트 변환
        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMypage(1);
            }
        });
    }
    private void viewMypage(int whr) {
        if(whr == 0) {      // 개인정보변경
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            PrivacyFragment PrFragment = new PrivacyFragment();
            transaction.replace(R.id.frame, PrFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(whr == 1) {     //AL,BL값 확인
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            CountFragment CtFragment = new CountFragment();
            transaction.replace(R.id.frame, CtFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
