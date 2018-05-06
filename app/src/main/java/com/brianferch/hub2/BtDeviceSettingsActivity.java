package com.brianferch.hub2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.ArrayList;

public class BtDeviceSettingsActivity extends AppCompatActivity {

    private ArrayList<SeekBar> eqControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_device_settings);

        Intent intent = getIntent();
        String btDeviceName = intent.getStringExtra(MainActivity.EXTRA_DEVICE_NAME);
        this.setTitle(btDeviceName);

        // TODO: Fields for this device should be retrieved here

        final SeekBar volumeBar = (SeekBar) findViewById(R.id.volume_bar);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO: Send volume change bluetooth message
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final LinearLayout eqControlsLayout = (LinearLayout) findViewById(R.id.eq_controls);
        eqControls = new ArrayList<>();
        for (int i = 0; i < eqControlsLayout.getChildCount(); i++) {
            eqControls.add((SeekBar) eqControlsLayout.getChildAt(i));
        }

        final Switch eqSwitch = (Switch) findViewById(R.id.eq_switch);
        this.setEqEnabled(eqSwitch.isChecked());
        eqSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEqEnabled(isChecked);
            }
        });
    }

    private void setEqEnabled(boolean enabled) {
        for (SeekBar eqBar : eqControls) {
            eqBar.setEnabled(enabled);
        }
    }
}
