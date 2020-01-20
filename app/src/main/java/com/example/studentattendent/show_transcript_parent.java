package com.example.studentattendent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class show_transcript_parent extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transcript_parent);

        Bundle bundle = getIntent().getExtras();
        String idmac = bundle.getString("Myidstd");
        String namemac = bundle.getString("Mynamestd");


        transcript transcript = new transcript();
        transcript.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutt,transcript).commit();

    }
}
