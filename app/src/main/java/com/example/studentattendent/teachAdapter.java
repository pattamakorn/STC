package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class teachAdapter extends RecyclerView.Adapter<teachAdapter.MyViewHolder> {

    Context mcontext;
    List<teach> mteach;
    View view;

    public teachAdapter(Context mcontext,List<teach> mteach) {
        this.mcontext = mcontext;
        this.mteach = mteach;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(mcontext).inflate(R.layout.item_timeteach,parent,false);
        teachAdapter.MyViewHolder vHolder = new teachAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.isub.setText(mteach.get(position).getIdsub());
        holder.room.setText(mteach.get(position).getClassR());
        holder.timeteach.setText(mteach.get(position).getTimeT());



    }

    @Override
    public int getItemCount() {
        return mteach.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView isub,room,timeteach;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            isub = itemView.findViewById(R.id.id_subject);
            room = itemView.findViewById(R.id.textView2);
            timeteach = itemView.findViewById(R.id.timetablesubject);

        }
    }
}
