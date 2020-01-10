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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class timetableparent extends Fragment {

    View view;

    private TextView dayparent,dateparent,termparent,yearparent;
    public timetableAdapter TimetableAdapter;
    private RecyclerView recyclerView;
    public List<stimetable> listtimetable;
    private String Url_Loadtimeble = "http://203.154.83.137/StudentAttendent/loadtimetable.php";


    public timetableparent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timetableparent, container, false);

        dayparent = view.findViewById(R.id.thomeparent);
        dateparent = view.findViewById(R.id.datehomeparent);
        termparent = view.findViewById(R.id.term1);
        yearparent = view.findViewById(R.id.year1);
        recyclerView = view.findViewById(R.id.recyclertimesparent);

        TimetableAdapter = new timetableAdapter(getContext(),listtimetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(TimetableAdapter);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE");
        String ct = simpleDateFormat.format(new Date());
        String dayn = simpleDayFormat.format(new Date());

        dayparent.setText("ตารางเรียน"+dayn);
        dateparent.setText(ct);
        //choosestudent();


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listtimetable = new ArrayList<>();
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
                params.put("term","1");
                params.put("day","วันจันทร์");
                params.put("year","2562");
                params.put("id","student");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
