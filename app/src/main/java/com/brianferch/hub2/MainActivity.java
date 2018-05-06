package com.brianferch.hub2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DEVICE_NAME = "com.gregcompany.gregbluetooth.DEVICE_NAME";

    private static ArrayList<BtDevice> btDeviceList = new ArrayList<BtDevice>() {{
        add(new BtDevice("Soundbot SB571"));
        add(new BtDevice("Sony - XB10"));
        add(new BtDevice("Anker Soundcore"));
        add(new BtDevice("JBL Flip 4"));
        add(new BtDevice("UE BOOM"));
        add(new BtDevice("Bose Soundlink"));
        add(new BtDevice("1"));
        add(new BtDevice("2"));
        add(new BtDevice("3"));
        add(new BtDevice("4"));
        add(new BtDevice("5"));
        add(new BtDevice("6"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: Full list of devices with connection states should be retrieved here

        final GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new BtDeviceAdapter(this, btDeviceList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                BtDevice selectedDevice = (BtDevice) gridview.getItemAtPosition(position);
                if (selectedDevice != null) {
                    switch (selectedDevice.getConnectionState()) {
                        case available:
                            selectedDevice.setConnectionState(BtDevice.stateEnum.connected);
                            ((BtDeviceAdapter) parent.getAdapter()).notifyDataSetChanged();
                            break;
                        case connected:
                            Intent intent = new Intent(MainActivity.this, BtDeviceSettingsActivity.class);
                            intent.putExtra(EXTRA_DEVICE_NAME, selectedDevice.getName());
                            startActivity(intent);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}