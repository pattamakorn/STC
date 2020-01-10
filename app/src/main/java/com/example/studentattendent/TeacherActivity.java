package com.example.studentattendent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherActivity extends AppCompatActivity{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new homeTeacher()).commit();
                    return true;
                case R.id.timetable:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new timeteacher()).commit();
                    return true;
                case R.id.checknames:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checkname()).commit();
                    return true;
                case R.id.transcript:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new transcript_teacher()).commit();
                    return true;
                case R.id.gps:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new gpsparent()).commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new homeTeacher()).commit();
        SharedPreferences sp = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        String showidpre = sp.getString("IdKey","No ID");
        String showfullpre = sp.getString("fullnameKey","No ID");
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
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new genQr()).commit();
                return true;
            case R.id.logout_menu:
                SharedPreferences sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(TeacherActivity.this,login.class));
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }
}
