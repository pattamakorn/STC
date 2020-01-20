package com.example.studentattendent;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class timetableselectstd extends Fragment {

    public showtimeformselectAdapter showtimeformselectAdapter;
    private RecyclerView recyclerView;
    private List<showtimeformselect> liststimese;
    View view;


    public timetableselectstd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timetableselectstd, container, false);

        recyclerView = view.findViewById(R.id.recyclerselectstdttime);

        showtimeformselectAdapter = new showtimeformselectAdapter(getContext(),liststimese);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(showtimeformselectAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadmystd();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liststimese = new ArrayList<>();
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
//        liststimese.add(new showtimeformselect(
//                "aad",
//                "aaad",
//                "ad",
//                "ddddddd"));
    }

    public void loadmystd(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/showmystd.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String mystdid = posts.getString("idmystd");
                        String smyname = posts.getString("funamemystd");
                        String smyimage = posts.getString("imagemystd");
                        String smyclass = posts.getString("classmystd");

                        liststimese.add(new showtimeformselect(
                                mystdid,
                                smyimage,
                                smyname,
                                smyclass));
//                        listsstd.add(new SSTDTran(
//                                "",
//                                "",
//                                "",
//                                "")
//                        );
                        showtimeformselectAdapter = new showtimeformselectAdapter(getContext(),liststimese);
                        recyclerView.setAdapter(showtimeformselectAdapter);

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
