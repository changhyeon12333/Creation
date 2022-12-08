package com.example.ble_train;

import android.bluetooth.BluetoothDevice;
import android.widget.ArrayAdapter;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<BluetoothDevice> mDevices;
    private int mViewResourceId;

    public DeviceListAdapter(Context context, int resourceId, ArrayList<BluetoothDevice> devices) {
        super(context, resourceId, devices);
        this.mDevices = devices;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = resourceId;
    }
}
