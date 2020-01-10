package com.example.studentattendent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class scanning extends AppCompatActivity {

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
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checkname()).commit();
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
        setContentView(R.layout.activity_scanning);

        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new scanning_fragment()).commit();

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
//                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new qrcode()).commit();
                startActivity(new Intent(scanning.this,scanning.class));
                return true;
            case R.id.logout_menu:
                SharedPreferences sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(scanning.this,login.class));
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }


}
