package com.debduttapanda.wifi_scanner;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    List<ScanResult> wifiList;

    public ListAdapter(Context context, List<ScanResult> wifiList) {
        this.context = context;
        this.wifiList = wifiList;

        inflater  = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.d("ItemCount", "${wifiList.size()}");
        return wifiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder;
        View view1 = view;
        if (view1 == null){
            view1 = inflater.inflate(R.layout.list_item, null);
            holder = new Holder();
            holder.tvDetails = (TextView) view1.findViewById(R.id.txtWiFiName);
            holder.singleItemClick = (RelativeLayout) view1.findViewById(R.id.rowRL);
            view1.setTag(holder);
        }else {
            holder = (Holder) view1.getTag();
        }

        holder.tvDetails.setText(wifiList.get(i).SSID);
        holder.singleItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, ""+wifiList.get(i).SSID, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AuthActivity.class);
                intent.putExtra("SSID", wifiList.get(i).SSID);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        return view1;
    }

    class Holder{
        TextView tvDetails;
        RelativeLayout singleItemClick;
    }
}
