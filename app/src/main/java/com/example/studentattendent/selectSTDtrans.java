package com.example.studentattendent;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class selectSTDtrans extends Fragment {

    public SSTDTranAdapter sstdTranAdapter;
    private RecyclerView recyclerView;
    private List<SSTDTran> listsstd;
    View view;


    public selectSTDtrans() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select_stdtrans, container, false);
        recyclerView = view.findViewById(R.id.recyclerselectstdtrans);

        sstdTranAdapter = new SSTDTranAdapter(getContext(),listsstd);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(sstdTranAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listsstd = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadmystd();

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

                        listsstd.add(new SSTDTran(
                                mystdid,
                                smyimage,
                                smyname,
                                "ชั้นมัธยมศึกษาปีที่ "+smyclass)
                        );
//                        listsstd.add(new SSTDTran(
//                                "",
//                                "",
//                                "",
//                                "")
//                        );
                        sstdTranAdapter = new SSTDTranAdapter(getContext(),listsstd);
                        recyclerView.setAdapter(sstdTranAdapter);

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
