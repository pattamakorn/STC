package com.example.studentattendent;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class timeteacher extends Fragment {
    private  String URL_TIMETeach = "http://203.154.83.137/StudentAttendent/loadteachtime.php";
    private TextView nowday,nowdate,showy,termtimeteach;
    public String today,toyear,toterm;
    public teachAdapter TeachAdapter;
    View view;


    private RecyclerView recyclerView;
    private List<teach> listteachtimetable;

    List<String> listspinners  = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapters;

    public timeteacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timeteacher, container, false);
        nowdate = view.findViewById(R.id.datett);
        nowday = view.findViewById(R.id.daytt);
        showy = view.findViewById(R.id.textyear);
        termtimeteach = view.findViewById(R.id.textterm);

        recyclerView = view.findViewById(R.id.recyclerteachertime);
        TeachAdapter = new teachAdapter(getContext(),listteachtimetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(TeachAdapter);


        termtimeteach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeterm();
            }
        });



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE");
        String ct = simpleDateFormat.format(new Date());
        String dayn = simpleDayFormat.format(new Date());
        today = dayn;
        toterm = "1";
        toyear = "2562";

        nowday.setText("Hello, "+dayn);
        nowdate.setText(ct);

        loadtime();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listteachtimetable = new ArrayList<>();
    }

    public void loadtime(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/loadteachtime.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String subid = posts.getString("idsubject");
                        String subname = posts.getString("subjectname");
                        String mclassroom = posts.getString("class");
                        String mtime = posts.getString("time");
                        listteachtimetable.add(new teach(
                                subid+" "+subname,
                                "ห้องเรียน "+mclassroom,
                                "เวลา "+mtime)
                        );
                        TeachAdapter = new teachAdapter(getContext(),listteachtimetable);
                        recyclerView.setAdapter(TeachAdapter);


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
                params.put("id",mid);
                params.put("term",toterm);
                params.put("day",today);
                params.put("year",toyear);
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
                    termtimeteach.setText("ภาคเรียนที่ 1");
                    toterm = "1";
                    listteachtimetable.clear();
                    TeachAdapter.notifyDataSetChanged();
                    loadtime();
                }
                else if(i == 1){
                    termtimeteach.setText("ภาคเรียนที่ 2");
                    toterm = "2";
                    listteachtimetable.clear();
                    TeachAdapter.notifyDataSetChanged();
                    loadtime();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = mbuilder.create();
        dialog.show();
    }

}
