package com.example.multi_apps;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bluetoothApp extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    private TextView mStatusBleTv, mPairedTv;
    ImageView mBlueIV;
    Button mOnBtn, mOffBtn, mback;
    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_app);
        mStatusBleTv = (TextView)findViewById(R.id.statusBluetoothTv);
        mPairedTv = (TextView)findViewById(R.id.pairTv);
        mBlueIV = (ImageView)findViewById(R.id.bluetoothIv);
        mOnBtn = (Button)findViewById(R.id.onButn);
        mOffBtn = (Button)findViewById(R.id.offButn);
        mback = (Button)findViewById(R.id.back);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            mStatusBleTv.setText("Bluetooth is not available");
        }
        else {
            mStatusBleTv.setText("Bluetooth is  available");


            if (bluetoothAdapter.isEnabled()) {
                mBlueIV.setImageResource(R.drawable.bluetoothon);
            } else {
                mBlueIV.setImageResource(R.drawable.bluetoothoff);

            }

            mOnBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bluetoothAdapter.isEnabled()) {
                        showToast("Turning on Bluetooth..");
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, REQUEST_ENABLE_BT);
                    } else {
                        showToast("Bluetooth is already on");

                    }
                }
            });


            mOffBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        showToast("Turning  Bluetooth off");
                        mBlueIV.setImageResource(R.drawable.bluetoothoff);
                    } else {
                        showToast("Bluetooth is already off");

                    }
                }
            });
            mback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(bluetoothApp.this,MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}