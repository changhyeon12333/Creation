package com.example.saferecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, container, false);
        btnCreateDatabase = (Button) v.findViewById(R.id.create_privacy_btn);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText etDBName = new EditText(PrivacyFragment.this);
                etDBName.setHint("개인정보를 입력하세요");

                AlertDialog.Builder dialog = new AlertDialog.Builder(PrivacyFragment.this);
                dialog.setTitle("이름을 ")
                        .setMessage(("이름을 입력하세요"))
                        .setView(etDBName)
                        .setPositiveButton("생성", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(etDBName.getText().toString().length()>0){
                                    dbHelper = new DBHelper(PrivacyFragment.this,
                                            etDBName.getText().toString(),
                                            null, 1);
                                }
                                dbHelper.testDB();

                                // Toast.makeText(MainActivity.this, etDBName.getText(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();

            }
        }));
        return inflater.inflate(R.layout.fragment_privacy, container, false);
        return v;
    }

}
