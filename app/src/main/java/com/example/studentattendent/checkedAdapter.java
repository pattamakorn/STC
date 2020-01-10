package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class checkedAdapter extends RecyclerView.Adapter<checkedAdapter.MyViewHolder> {
    Context ccontext;
    List<checked> mcheck;
    View view;

    public checkedAdapter(Context ccontext,List<checked> mcheck){
        this.ccontext = ccontext;
        this.mcheck = mcheck;
    }

    @NonNull
    @Override
    public checkedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(ccontext).inflate(R.layout.item_studentscaned,parent,false);
        checkedAdapter.MyViewHolder vHolder = new checkedAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull checkedAdapter.MyViewHolder holder, int position) {
        holder.getstd.setText(mcheck.get(position).getNamestdcheck());
        holder.gettimec.setText(mcheck.get(position).getTimecheck());

    }

    @Override
    public int getItemCount() {
        return mcheck.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView getstd,gettimec;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            getstd = itemView.findViewById(R.id.stidname);
            gettimec = itemView.findViewById(R.id.timecheck);

        }
    }
}
