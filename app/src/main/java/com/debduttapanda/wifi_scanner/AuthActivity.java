package com.debduttapanda.wifi_scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class AuthActivity extends AppCompatActivity {

    TextView ssid;
    String getSSID;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ssid = findViewById(R.id.getSSID);
        password = findViewById(R.id.edt_password);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                ssid = null;
            } else {
                getSSID = extras.getString("SSID");
            }
        } else {
            getSSID = (String) savedInstanceState.getSerializable("SSID");
        }

        ssid.setText(getSSID);

        //getSSID
        //password.getText().toString()
    }

    public void connectBtn(View view) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", getSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", password.getText().toString());

        @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
//remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
        Log.d("MyLogs",""+netId);
    }/////////////////////////////////////////

}