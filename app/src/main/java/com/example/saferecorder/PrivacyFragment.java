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

public class PrivacyFragment extends Fragment implements View.OnClickListener{

    // 디자인 변수 선언
    Button btnSave;
    Button btnSelect;

    EditText edtName;
    EditText edtAge;
    EditText edtAddr;
    TextView viewResult;

    // DBHelper
    DBHelper dbHelper;


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, container, false);
        // Control 매핑
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnSelect = (Button) v.findViewById(R.id.btnSelect);
        edtName = (EditText) v.findViewById(R.id.edtName);
        edtAge = (EditText) v.findViewById(R.id.edtAge);
        edtAddr = (EditText) v.findViewById(R.id.edtAddr);
        viewResult = (TextView) v.findViewById(R.id.txtResult);

        // 버튼 클릭 이벤트 정의
        btnSave.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

        dbHelper = new DBHelper(PrivacyFragment.this, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                dbHelper.insert(edtName.getText().toString(), Integer.parseInt(edtAge.getText().toString()), edtAddr.getText().toString());
                break;
            case R.id.btnSelect:
                viewResult.setText(dbHelper.getResult());
                break;
        }
    }
}
