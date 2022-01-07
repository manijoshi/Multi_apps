package com.example.multi_apps;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WifiApp extends AppCompatActivity {
    Button enableButton,disableButton,mback;
    WifiManager wifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_app);

        enableButton=(Button)findViewById(R.id.button1);
        disableButton=(Button)findViewById(R.id.button2);
        mback = (Button)findViewById(R.id.back);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        enableButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                wifi.setWifiEnabled(true);
                Toast.makeText(WifiApp.this, "Wifi turned on", Toast.LENGTH_SHORT).show();
            }
        });

        disableButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                wifi.setWifiEnabled(false);
                Toast.makeText(WifiApp.this, "Wifi turned off", Toast.LENGTH_SHORT).show();
            }
        });
        mback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WifiApp.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}