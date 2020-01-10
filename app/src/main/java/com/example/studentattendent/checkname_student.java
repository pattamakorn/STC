package com.example.studentattendent;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
public class checkname_student extends Fragment {

    View view;
    private int agh;

    TimeTable timeTable;

    private TextView sdate,inc;

    public studentcheckAdapter StudentcheckAdapter;
    private RecyclerView recyclerViewcs;
    private List<studentcheck> listcheckst;
    public String numsub,numcheck,counttime,date;
    public int percen,aa,bb;


    public checkname_student() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkname_student, container, false);
        recyclerViewcs = view.findViewById(R.id.recyclerstdfollow);
        sdate = view.findViewById(R.id.follow_date);
        inc = view.findViewById(R.id.follow_inclass);

        StudentcheckAdapter = new studentcheckAdapter(getContext(),listcheckst);
        recyclerViewcs.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewcs.setAdapter(StudentcheckAdapter);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ct = simpleDateFormat.format(new Date());
        sdate.setText(ct);
        SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE");
        date = simpleDayFormat.format(new Date());
        //Toast.makeText(getActivity(),date, Toast.LENGTH_SHORT).show();


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listcheckst = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadcheck();
    }

    public void loadcheck(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/loadcheckedstudent.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);

                        listcheckst.add(new studentcheck(
                                ""+posts.getString("idsub"),
                                ""+posts.getString("namesub"),
                                ""+posts.getString("idteach"),
                                ""+posts.getString("fnameteach")+" "+posts.getString("lnameteach"),
                                ""+posts.getString("classroom"),
                                ""+posts.getString("idscan")
                        ));
                        StudentcheckAdapter = new studentcheckAdapter(getContext(),listcheckst);
                        recyclerViewcs.setAdapter(StudentcheckAdapter);
                        loadcounttime();


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
                params.put("myid",mid);
                params.put("day",date);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadcounttime(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/counttimetable.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        counttime = posts.getString("countsubject");

                        int tcount = Integer.parseInt(counttime);
                        int countcheck = listcheckst.size();
                        int percent = (countcheck*100)/tcount;
                        String lo = String.valueOf(percent);
                        inc.setText("เข้าเรียน "+lo+"%");

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
                params.put("myid",mid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
