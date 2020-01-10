package com.example.studentattendent;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTable extends Fragment {
    private TextView daynow,datenow,numterm,term;
    private Spinner spinner;
    String myear,mday,dday,year="2562";
    String myuser,myname;
    public timetableAdapter TimetableAdapter;

    public int countsub;

    View v;

    private RecyclerView recyclerView;
    public List<stimetable> listtimetable;
    private String Url_Loadtimeble = "http://203.154.83.137/StudentAttendent/loadtimetable.php";


    public TimeTable() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_time_table, container, false);

        recyclerView = v.findViewById(R.id.recyclertime);
        daynow = v.findViewById(R.id.daynow);
        datenow = v.findViewById(R.id.datenow);
        numterm = v.findViewById(R.id.numterm);
        term = v.findViewById(R.id.term);
        TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(TimetableAdapter);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE");
        String ct = simpleDateFormat.format(new Date());
        String dayn = simpleDayFormat.format(new Date());

        dday = dayn;
        numterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeterm();
            }
        });

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeterm();
            }
        });


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                terms = listspinner.get(i);
//                listtimetable.clear();
//                TimetableAdapter.notifyDataSetChanged();
//                loadtime();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        datenow.setText(ct);
        daynow.setText(dday);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listtimetable = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadtime();
    }

    public void loadtime(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Url_Loadtimeble, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String subid = posts.getString("subid");
                        String subname = posts.getString("subname");
                        String teacherfname = posts.getString("fnameT");
                        String teacherlname = posts.getString("lnameT");
                        String lpname = posts.getString("term");
                        String tpname = posts.getString("year");
                        String tsname = posts.getString("day");
                        String time = posts.getString("time");
                        String classroom = posts.getString("classroom");

                        listtimetable.add(new stimetable(
                                subid,
                                subname,
                                "ครูประจำวิชา "+teacherfname+" "+teacherlname,
                                "ห้องเรียน "+classroom,
                                time)
                        );
                        TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
                        recyclerView.setAdapter(TimetableAdapter);

                        countsub = listtimetable.size();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                params.put("term",numterm.getText().toString());
                params.put("day",dday);
                params.put("year",year);
                params.put("id",mid);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void changeterm(){
        final String[] listItems = {"ภาคเรียนที่ 1","ภาคเรียนที่ 2"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
        mbuilder.setTitle("เลือกภาคเรียน");
        mbuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    numterm.setText("1");
                    listtimetable.clear();
                    TimetableAdapter.notifyDataSetChanged();
                    loadtime();
                }
                else if(i == 1){
                    numterm.setText("2");
                    listtimetable.clear();
                    TimetableAdapter.notifyDataSetChanged();
                    loadtime();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = mbuilder.create();
        dialog.show();
    }


}
