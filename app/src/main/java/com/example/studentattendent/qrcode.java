package com.example.studentattendent;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class qrcode extends Fragment {
    View view;
    public String inputkey;
    public EditText inkey;
    public ImageView goscan;
    public Button check;


    public qrcode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_qrcode, container, false);
        inkey = view.findViewById(R.id.inputkeyword);
        goscan = view.findViewById(R.id.gotoscan);
        check = view.findViewById(R.id.checknameonqr);

        goscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getChildFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();
                startActivity(new Intent(getActivity(),scanning.class));
            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scankeywordcode();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("เช็คชื่อสำเร็จ!!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(" ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    public void scankeywordcode(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/StudentAttendent/scaned.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                        // Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                params.put("id",mid);
                params.put("keyword",inkey.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
