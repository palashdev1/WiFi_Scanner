package com.debduttapanda.wifi_scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
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
    String ssid_password;

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
        //ssid_password.getText(ssid_password);
    }

    public void connectBtn(View view) {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + getSSID + "\"";
        conf.wepKeys[0] = "\"" + password.getText() + "\"";
        conf.wepTxKeyIndex = 0;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

        conf.preSharedKey = "\"" + password.getText() + "\"";
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

        Log.d("MyTag", String.valueOf(conf));

        @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);
        Log.d("MyTag", String.valueOf(conf.allowedAuthAlgorithms));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            Log.d("MyTag", list.toString());
            if(i.SSID != null && i.SSID.equals("\"" + getSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }
    }
}