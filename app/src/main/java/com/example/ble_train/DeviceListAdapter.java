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
    public View getView(int position , View convertView, ViewGroup parent){
        convertView = mLayoutInflater.inflate(mViewResourceId,null);
        BluetoothDevice device = mDevices.get(position);

        if(device!=null){
            TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
            TextView deviceAddress = (TextView) convertView.findViewById(R.id.tvDeviceAddress);
            //원래 null검사 해야하는데 검색되는 것중 null인게 있어서 뺌
            //if(deviceName !=null){
            deviceName.setText(device.getName());
            //}
            //else if(deviceAddress!=null){
            deviceAddress.setText(device.getAddress());
            //}
        }
        return convertView;
    }

}
