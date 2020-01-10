package com.example.studentattendent;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import java.util.HashMap;
import java.util.Map;

public class teachposttext extends AppCompatDialogFragment {
    private EditText edtextpost,topi;
    View view;
    private ImageView imgpostt;
    private TextView textnamee;
    private String getimg;
    private teachposttextListener listener;
    private String textPost,topicPost;
    private String URL_postnews = "http://203.154.83.137/StudentAttendent/postnews.php";
    private String TURL_Profile = "http://203.154.83.137/StudentAttendent/loaduserteacher.php";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.post_dialog,null);
        builder.setView(view)
                .setTitle("ข่าวสาร \n \n")
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("โพสต์", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textPost = edtextpost.getText().toString();
                        topicPost = topi.getText().toString();
                        insertpost();
                    }
                });
        loadprofileteacher();
        edtextpost = view.findViewById(R.id.postedittext);
        topi = view.findViewById(R.id.posttopic);
        imgpostt = view.findViewById(R.id.imgpost);
        textnamee = view.findViewById(R.id.textname);
        return builder.create();
    }

    public interface  teachposttextListener{
        void applyTexts(String textPost);

    }

    public void insertpost(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_postnews, new Response.Listener<String>() {
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
                params.put("nameteachpost",mid);
                params.put("topic",topicPost);
                params.put("pnews",textPost);
                params.put("urlimg",getimg);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadprofileteacher(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                TURL_Profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String imgP = posts.getString("imgT");
                        String fnameT = posts.getString("fnameteacher");
                        String lnameT = posts.getString("lnameteacher");
                        Glide.with(view.getContext()).load(imgP).into(imgpostt);
                        textnamee.setText(fnameT+" "+lnameT);
                        getimg = imgP;
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
