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

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_close=findViewById(R.id.app_down_btn);
        btn_priv=findViewById(R.id.priv_cng_btn);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 마이페이지 클릭시 프레그먼트 변환
        btn_priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMypage(0);
            }
        });
    }
}