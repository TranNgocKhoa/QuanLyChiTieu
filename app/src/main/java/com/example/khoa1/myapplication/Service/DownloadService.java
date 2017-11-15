package com.example.khoa1.myapplication.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Created by khoa1 on 11/8/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class DownloadService extends IntentService {
    public DownloadService(String name) {
        super(name);
    }
//    public static final String SERVICE_NAME = "DownloadService";
//    public DownloadService() {
//        super(SERVICE_NAME);
//    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String[] data = intent.getStringArrayExtra("DATA_KEY");



    }
}
