package com.example.saferecorder;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        }
    }
