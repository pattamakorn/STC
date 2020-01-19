package com.example.studentattendent;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

public class MyLocationService extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE = "com.example.studentattendent.UPDATE_LOCATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            final String action = intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);
                if(result != null){
                    Location location = result.getLastLocation();

                    try{
                        Toast.makeText(context,"sadsdsad",Toast.LENGTH_LONG).show();
                     MainActivity.getInstance().updatelocation(String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));
                        Log.d("asdsad","asdadasda");
                    }catch (Exception ex){
                        Log.d("asdsad","asdadasda");
                        Toast.makeText(context,"asdasdasdas",Toast.LENGTH_LONG).show();
                        MainActivity.getInstance().updatelocation(String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));
                    }
                }
            }
        }
    }
}
