package com.example.studentattendent;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    Marker marker;
    LocationListener locationListener;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;

    static MainActivity instance;

    public static MainActivity getInstance(){
        return instance;
    }
//    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();
                    return true;
                case R.id.timetable:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new TimeTable()).commit();
                    return true;
                case R.id.checknames:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checkname_student()).commit();
                    return true;
                case R.id.transcript:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new transcript()).commit();
                    return true;
                case R.id.gps:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new maps()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();

        SharedPreferences sp = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        String showidpre = sp.getString("IdKey","No ID");
        String showfullpre = sp.getString("fullnameKey","No ID");

        instance = this;


//        startService(new Intent(MainActivity.this,bgservice.class));

        Dexter.withActivity(this)
                .withPermission(ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        updateLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this,"กรุณาเปิดการอนุญาตเข้าถึงโลเคชั่นก่อน",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemtop) {
        int id = itemtop.getItemId();
        switch (id) {
            case R.id.qrcode_menu:
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new qrcode()).commit();
                //startActivity(new Intent(MainActivity.this,scanning.class));
                return true;
            case R.id.logout_menu:
                SharedPreferences sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(MainActivity.this,login.class));
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }

    public void LoadLocation(){

    }

    private void updateLocation() {
        buildLocationRequest();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,getPendingIntend());
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        ;locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }

    private PendingIntent getPendingIntend(){
          Intent intent = new Intent(this,MyLocationService.class);
          intent.setAction(MyLocationService.ACTION_PROCESS_UPDATE);
          return PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void updatelocation(final String slongitude, final String slatitude){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/trackingupdate.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseTrackGPS",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                        // Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = (MainActivity.this).getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                params.put("idst",mid);
                params.put("lati",slatitude);
                params.put("longti",slongitude);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


}
