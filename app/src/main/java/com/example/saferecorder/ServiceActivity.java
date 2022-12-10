package com.example.saferecorder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.icu.util.Output;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.saferecorder.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class ServiceActivity extends AppCompatActivity {
    private static String IP_ADDRESS = "http://dtp06077.dothome.co.kr/Login2.php"; //IP주소
    private static String ACELL = "ACELL"; //ACELL
    private static String BREAK = "BREAK"; //BREAK

    private GpsTracker gpsTracker; //GPS
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    Button btnBluetoothOn;
    Button btnBluetoothOff;

    TextView tvBluetoothStatus;
    Button btnConnect;

    BluetoothAdapter mBluetoothAdapter;

    Button btnDiscover;
    public ArrayList<BluetoothDevice> mBTdevices;
    public DeviceListAdapter mDeviceListAdapter;
    ListView newDevicesList;
    Button btnFindDiscover;

    //BT SETTING
    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }


        //initialize
        btnBluetoothOn = findViewById(R.id.btnBluetoothOn);
        btnBluetoothOff = findViewById(R.id.btnBluetoothOff);
        tvBluetoothStatus = findViewById(R.id.tvBluetoothStatus);
        btnConnect = findViewById(R.id.btnConnect);
        btnFindDiscover = findViewById(R.id.btnFindDiscover);
        newDevicesList = (ListView) findViewById(R.id.newDevicesList);
        Button ShowLocationButton = (Button) findViewById(R.id.btnSendData);
        mBTdevices = new ArrayList<>();

        //get Defualt of blooth adapter | get Permitted(Manifest)
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //1. Bluetooth on/off
        btnBluetoothOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothOn();
            }
        });