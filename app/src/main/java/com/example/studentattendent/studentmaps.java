package com.example.studentattendent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class studentmaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    Marker marker;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmaps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality()+":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (marker != null){
                        marker.remove();
                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                    }
                    else{
                        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 21.0f));
                    }
                    String lati = String.valueOf(latitude);
                    String longti = String.valueOf(longitude);
                    Toast.makeText(studentmaps.this, "Latitude: "+lati
                            +"\nLongtitude: "+longti, Toast.LENGTH_SHORT).show();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }
}
