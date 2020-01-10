package com.example.studentattendent;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class genQr extends Fragment {
    View v;
    private ImageView qrc;
    private TextView skeyword,save,namesub;
    private Button gen;
    private String  URL_INQRCODE= "http://203.154.83.137/StudentAttendent/qrcode/gen.php",tt,sqr,idsub;
    BitmapDrawable bitmapDrawable;
    Bitmap bitmap;

    public genQr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_gen_qr, container, false);

        qrc = v.findViewById(R.id.qrc);
        skeyword = v.findViewById(R.id.showkey);
        gen = v.findViewById(R.id.genqr);
        save = v.findViewById(R.id.saveQR);
        namesub = v.findViewById(R.id.namesub);
        loadprofileteacher();
        Drawable drawable = getResources().getDrawable(R.drawable.qrcode);
        bitmap = ((BitmapDrawable) drawable).getBitmap();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Save Image", Toast.LENGTH_SHORT).show();
                ContextWrapper cw = new ContextWrapper(getContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                File file = new File(directory, "UniqueFileName" + ".jpg");
                if (!file.exists()) {
                    Log.d("path", file.toString());
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        Toast.makeText(cw, "Success!!", Toast.LENGTH_SHORT).show();
                        fos.flush();
                        fos.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Korn", Toast.LENGTH_SHORT).show();
                sqr = getRandomString(6);
                skeyword.setText("Keyword: "+sqr);
                Toast.makeText(getActivity(), ""+sqr, Toast.LENGTH_SHORT).show();
                insertpost();
            }
        });

        return v;


    }

    public static String getRandomString(int i){

        final String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder result = new StringBuilder();
        while (i > 0){

            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            i--;
        }

        return result.toString();
    }

    public void insertpost(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_INQRCODE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                tt = response.toString();
                Glide.with(getActivity()).load(tt).into(qrc);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
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
                params.put("keyword",sqr);
                params.put("idsub",idsub);
                params.put("myid",mid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadprofileteacher(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/loaduserteacher.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String subjectname = posts.getString("namesubject");
                        idsub = posts.getString("idsubject");
                        namesub.setText("วิชา "+idsub+" "+subjectname);
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

}
