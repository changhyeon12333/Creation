package com.example.saferecorder


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.inappmessaging.model.Button


public class OpenActivity : AppCompatActivity() {

    private var btn_login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
        btn_login = findViewById<Button>(R.id.lg_saferecorder_btn)

        val private = null
        private Button btn_login;
        // 운전시작 버튼 클릭시 서비스 페이지로 이동
        btn_login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@OpenActivity, LoginActivity::class.java)
            startActivity(intent)
        })

    }
}

