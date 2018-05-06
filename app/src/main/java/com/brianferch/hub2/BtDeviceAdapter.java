package com.brianferch.hub2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;

class BtDeviceAdapter extends ArrayAdapter<BtDevice> {

    BtDeviceAdapter(Context context, ArrayList<BtDevice> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        System.out.println("id " + getItemId(position));
        System.out.println("postion " + position);

        final BtDeviceView deviceView;

        if (convertView == null) {
            deviceView = new BtDeviceView(getContext());
        } else {
            deviceView = (BtDeviceView) convertView;
        }

        final BtDevice btDevice = getItem(position);

        if (btDevice != null) {
            ImageButton clearButton = (ImageButton) deviceView.findViewById(R.id.disconnect_button);
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btDevice.setConnectionState(BtDevice.stateEnum.available);
                    notifyDataSetChanged();
                }
            });

            deviceView.setDeviceModel(btDevice);
        }

        return deviceView;
    }
}
