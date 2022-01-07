package com.example.multi_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView mapps;
    Button mcalculator, mbluetooth, mwifi, mflash, mcamera, mmusicplayer, mquiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapps = (TextView)findViewById(R.id.apps);
        mcalculator = (Button)findViewById(R.id.calculator);
        mbluetooth = (Button)findViewById(R.id.bluetooth);
        mwifi = (Button)findViewById(R.id.wifi);
        mflash = (Button)findViewById(R.id.flash);
        mcamera = (Button)findViewById(R.id.camera);
        mmusicplayer = (Button)findViewById(R.id.music);
        mquiz = (Button)findViewById(R.id.quiz);
        mcalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,calculatorApp.class);
            startActivity(intent);
            finish();
        });
        mbluetooth.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,bluetoothApp.class);
            startActivity(intent);
            finish();
        });

        mwifi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,WifiApp.class);
            startActivity(intent);
            finish();
        });
        mflash.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,FlashApp.class);
            startActivity(intent);
            finish();
        });
        mcamera.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,CameraApp.class);
            startActivity(intent);
            finish();
        });
        mmusicplayer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,MusicPlayerApp.class);
            startActivity(intent);
            finish();
        });
        mquiz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,QuizApp.class);
            startActivity(intent);
            finish();
        });

    }
}