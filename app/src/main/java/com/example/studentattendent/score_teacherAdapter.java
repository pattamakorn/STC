package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class score_teacherAdapter extends RecyclerView.Adapter<score_teacherAdapter.MyViewHolder> {

    Context smcontext;
    List<score_teacher> mscore;
    View view;

    public score_teacherAdapter(Context smcontext, List<score_teacher> mscore) {
        this.smcontext = smcontext;
        this.mscore = mscore;
    }

    @NonNull
    @Override
    public score_teacherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(smcontext).inflate(R.layout.item_transcript_teacher,parent,false);
        MyViewHolder vHolder = new MyViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull score_teacherAdapter.MyViewHolder holder, int position) {

        holder.fname.setText(mscore.get(position).getFullnamestd());
        holder.score.setText(mscore.get(position).getScore());
        holder.mid.setText(mscore.get(position).getMidterm());
        holder.fi.setText(mscore.get(position).getSfinal());
        holder.ssc.setText(mscore.get(position).getSumscore());

    }

    @Override
    public int getItemCount() {
        return mscore.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView fname,score,mid,fi,ssc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.namesubject_ttran);
            score = itemView.findViewById(R.id.score_ttran);
            mid = itemView.findViewById(R.id.score1_ttran);
            fi = itemView.findViewById(R.id.score2_ttran);
            ssc = itemView.findViewById(R.id.scoresum_ttran);
        }
    }
}
