package com.example.studentattendent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeparent:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new homeparent()).commit();
                    return true;
                case R.id.timetableparent:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new timetableparent()).commit();
                    return true;
                case R.id.checknamesparent:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checknamesparent()).commit();
                    return true;
                case R.id.transcriptparent:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new transcriptparent()).commit();
                    return true;
                case R.id.gpsparent:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new gpsparent()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new homeparent()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuparenttop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemtop) {
        int id = itemtop.getItemId();
        switch (id) {
            case R.id.logoutparent_menu:
                SharedPreferences sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(ParentActivity.this,login.class));
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }
}
