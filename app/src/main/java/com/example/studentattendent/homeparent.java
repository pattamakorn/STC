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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeparent extends Fragment {

    private TextView parentname;
    private ImageView picprofile,moredetail;
    View view;

    private RecyclerView recyclerView;
    private List<news> listnews;

    public homeparent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homeparent, container, false);

        parentname = view.findViewById(R.id.parentname);
        picprofile = view.findViewById(R.id.parentProfile);
        moredetail = view.findViewById(R.id.moredetail);


        parentname.setText("Korn Pattamakorn");

        Glide.with(view.getContext()).load("https://firebasestorage.googleapis.com/v0/b/tsb1-df2ab.appspot.com/o/korn.jpg?alt=media&token=8fdbf4a1-77ab-43ce-b164-739789e65540").
                into(picprofile);

        moredetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = view.findViewById(R.id.recyclernewsparent);
        newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(NewsAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listnews = new ArrayList<>();
        loadnews();
        loadprofile();

    }

    public void loadprofile(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/loadprofileparent.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fnameS = posts.getString("fullname");
                        String imgP = posts.getString("img");
                        Glide.with(view.getContext()).load(imgP).into(picprofile);
                        parentname.setText(fnameS);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                params.put("user",mid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadnews(){
        StringRequest stringRequest = new StringRequest(
                "http://203.154.83.137/StudentAttendent/loadnews.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String TFname = posts.getString("ptfname");
                        String TLname = posts.getString("ptlname");
                        String Txtpost = posts.getString("textnews");
                        String timenowPost = posts.getString("Tpost");
                        listnews.add(new news(
                                TFname+" "+TLname,
                                "หัวข้อเรื่อง : "+posts.getString("topic"),
                                Txtpost,
                                timenowPost,
                                posts.getString("img"))
                        );
                        newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
                        recyclerView.setAdapter(NewsAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
