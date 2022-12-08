package com.example.saferecorder;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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

        btnFindDiscover.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bluetoothDiscover();
            }
        });


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
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
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
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);//BT_REQUEST_ENABLE : ModeId

            IntentFilter BTIntent = new IntentFilter(mBluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadCastReceiver, BTIntent);

        }
    }
    private final BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(getApplicationContext(), "Bluetooth On", Toast.LENGTH_SHORT).show();
                        tvBluetoothStatus.setText("Active");
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(getApplicationContext(), "Bluetooth Off", Toast.LENGTH_SHORT).show();
                        tvBluetoothStatus.setText("NonActive");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(getApplicationContext(), "Bluetooth turning On", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(getApplicationContext(), "Bluetooth turning Off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }//end if
        }//end onReceive
    };
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(), "onDestroy called", Toast.LENGTH_SHORT).show();
        Log.d("onDestroy", "onDestroy called");
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkBTPermissions(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){

        }
    }
    public void bluetoothDiscover(){
        if(mBluetoothAdapter.isDiscovering()){ //already discovering > cancel > discover
            mBluetoothAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(), "Canceling discovery", Toast.LENGTH_SHORT).show();

            checkBTPermissions(); //check for permissions

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadCastReceiver,discoverDevicesIntent);
        }

        else if(!mBluetoothAdapter.isDiscovering()){
            checkBTPermissions();
            Toast.makeText(getApplicationContext(), "Starting discovery", Toast.LENGTH_SHORT).show();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadCastReceiver,discoverDevicesIntent);

        }
    }



}
