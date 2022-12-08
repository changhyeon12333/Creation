package com.example.saferecorder;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ServiceActivity extends AppCompatActivity {
    Button btnBluetoothOn;
    Button btnBluetoothOff;

    TextView tvBluetoothStatus;
    BluetoothAdapter mBluetoothAdapter;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //initialize
        btnBluetoothOn = findViewById(R.id.btnBluetoothOn);
        btnBluetoothOff = findViewById(R.id.btnBluetoothOff);
        tvBluetoothStatus = findViewById(R.id.tvBluetoothStatus);

        //get Defualt of blooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btnBluetoothOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothOn();
            }
        });
        btnBluetoothOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothOff();
            }
        });

    }

    public void blueToothOff() {
        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Already OFF", Toast.LENGTH_SHORT).show();
        } else if (mBluetoothAdapter.isEnabled()) {
            //Toast.makeText(getApplicationContext(), "Bluetooth Off",Toast.LENGTH_SHORT).show();
            mBluetoothAdapter.disable();
        }
    }

    public void blueToothOn() {
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "This device doesn't support bluetooth service", Toast.LENGTH_SHORT).show();
            tvBluetoothStatus.setText("NonActive");
        } else if (mBluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Already On", Toast.LENGTH_SHORT).show();
        } else {
            Intent intentBluetoothEnable = new Intent(mBluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);//BT_REQUEST_ENABLE : ModeId

            IntentFilter BTIntent = new IntentFilter(mBluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadCastReceiver, BTIntent);

        }
    }
    private final BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

        }//end onReceive
    };


}
