package com.example.saferecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.saferecorder.DBHelper;

public class PrivacyFragment extends Fragment {

    private Button btnCreateDatabase;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_privacy);

        btnCreateDatabase = (Button) findViewById(R.id.create_privacy_btn);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText etDBName = new EditText(PrivacyFragment.this);
                etDBName.setHint("DB명을 입력하세요");

                AlertDialog.Builder dialog = new AlertDialog.Builder(PrivacyFragment.this);
                dialog.setTitle("Database 이름을 ")
}
