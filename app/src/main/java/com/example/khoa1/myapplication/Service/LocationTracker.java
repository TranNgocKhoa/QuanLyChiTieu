package com.example.khoa1.myapplication.Service;

/**
 * Created by khoa1 on 12/10/2017.
 */

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;


import com.example.khoa1.myapplication.Database.SQLiteDanhGia;
import com.example.khoa1.myapplication.MainActivity;
import com.example.khoa1.myapplication.Model.DanhGia;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class LocationTracker extends Service {

    public static LocationListener gpsloclistener;
    public static LocationListener netlistener;
    public static LocationManager mLocationManager;
    public static Location gpsLOc;
    public static Location netLOc;
    private static Location gpslocation;
    private static Location netlocation;
    public static boolean isGPSEnabled;
    public static boolean isNetworkEnabled;
    private SQLiteDanhGia sqLiteDanhGia;


    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        sqLiteDanhGia = new SQLiteDanhGia(this);
        gpsloclistener = new gpsloclistener();
        netlistener = new netlistener();
        getLocation();
        // myfunc();

    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @SuppressWarnings("MissingPermission")
    public void getLocation() {
        try {
            mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGPSEnabled || isNetworkEnabled) {
                if (isGPSEnabled) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 180000, 0, gpsloclistener);
                }
                if (isNetworkEnabled) {
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, netlistener);//aslan bar roye network nemishe mahdodiyat gozasht
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class gpsloclistener implements LocationListener {
        @Override
        public void onLocationChanged(final Location location) {
            if (location.getAccuracy() < 20) {
                ArrayList<DanhGia> arrayListDanhGia = sqLiteDanhGia.getListDanhGia();
                for (DanhGia danhGia : arrayListDanhGia
                        ) {
//                    if (distFrom((double) danhGia.getLatitude(), (double) danhGia.getLongtitude(),
//                            location.getLatitude(), location.getLongitude()) < 30) {
//                        notification_id = (int) System.currentTimeMillis();
//
//                        Intent button_intent = new Intent("button_click");
//                        button_intent.putExtra("id",notification_id);
//                        PendingIntent button_pending_event = PendingIntent.getBroadcast(context,notification_id,
//                                button_intent,0);
//
//                        remoteViews.setOnClickPendingIntent(R.id.button,button_pending_event);
//
//                        Intent notification_intent = new Intent(context,MainActivity.class);
//                        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);
//
//                        builder.setSmallIcon(R.drawable.account)
//                                .setAutoCancel(true)
//                                .setCustomBigContentView(remoteViews)
//                                .setContentIntent(pendingIntent);
//
//                        notificationManager.notify(notification_id,builder.build());
//
//                    }
                }
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

    }
    private class netlistener implements LocationListener {
        @Override
        public void onLocationChanged(final Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            getLocation();
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;
        double v = dist * meterConversion;
        return v;
    }
}