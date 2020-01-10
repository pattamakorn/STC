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
public class Home extends Fragment implements detailstudent.detailListener{
    View v;
    public String URL_Profile = "http://203.154.83.137/StudentAttendent/studentprofile.php"
            ,mid;
    private ImageView imgprofile,dtail;
    private TextView stdname,stdclass,teachername,teachertel;

    private RecyclerView recyclerView;
    private List<news> listnews;
    private String Url_Loadnews = "http://203.154.83.137/StudentAttendent/loadnews.php";


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        imgprofile = v.findViewById(R.id.rentProfile);
        stdname = v.findViewById(R.id.fullname);

        recyclerView = v.findViewById(R.id.recycler);
        newsAdapter NewsAdapter = new newsAdapter(getContext(),listnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(NewsAdapter);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dtail = v.findViewById(R.id.showdetail);
        dtail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                //Toast.makeText(getActivity(), "Detail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listnews = new ArrayList<>();
        SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        mid = sp.getString("IdKey","No ID");
        String showfullpre = sp.getString("fullnameKey","No ID");
        loadnews();
        //insertpost();
        loadprofile();

    }

    public void loadprofile(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_Profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fnameS = posts.getString("fullname");
                        String imgP = posts.getString("img");
                        Glide.with(v.getContext()).load(imgP).into(imgprofile);
                        stdname.setText(fnameS);
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
                params.put("user",mid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadnews(){
        StringRequest stringRequest = new StringRequest(
            Url_Loadnews, new Response.Listener<String>() {
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


    public void openDialog(){
        detailstudent Detailstudent = new detailstudent();
        Detailstudent.show(getChildFragmentManager(),"testDialog");

    }

    @Override
    public void applyTexts(String textPost) {


    }
}
