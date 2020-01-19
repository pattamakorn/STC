package com.example.studentattendent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class bgservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        test();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
    }
    public void test(){
        int a = 5;
        int b = 10;
        int c = a+b;
//        Toast.makeText(this, "Num Test="+String.valueOf(c), Toast.LENGTH_SHORT).show();
    }
}
