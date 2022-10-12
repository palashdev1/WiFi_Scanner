package com.debduttapanda.wifi_scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WifiChangeBroadcastReceiver extends BroadcastReceiver {
    private final WifiChangeBroadcastListener listener;

    public WifiChangeBroadcastReceiver(WifiChangeBroadcastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        listener.onWifiChangeBroadcastReceived(context, intent);
    }

    public interface WifiChangeBroadcastListener {
        void onWifiChangeBroadcastReceived(Context context, Intent intent);
    }
}
