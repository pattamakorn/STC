package com.example.studentattendent;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class detailtranscriptstudentAdapter extends RecyclerView.Adapter<detailtranscriptstudentAdapter.MyViewHolder>{

    Context mcontext;
    List<detailtranscriptstudent> mtrannn;
    View view;
    Dialog myDialogrec;

    public detailtranscriptstudentAdapter(Context mcontext, List<detailtranscriptstudent> mtrannn) {
        this.mcontext = mcontext;
        this.mtrannn = mtrannn;
    }

    @NonNull
    @Override
    public detailtranscriptstudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.itemdetailtransciptstd,parent,false);
        final detailtranscriptstudentAdapter.MyViewHolder vHolder = new detailtranscriptstudentAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull detailtranscriptstudentAdapter.MyViewHolder holder, int position) {

        holder.dgrade.setText(mtrannn.get(position).getTrangrade());
        holder.didsub.setText(mtrannn.get(position).getTranid());
        holder.dnamesub.setText(mtrannn.get(position).getTransubname());
    }

    @Override
    public int getItemCount() {
        return mtrannn.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dgrade,didsub,dnamesub;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dgrade = itemView.findViewById(R.id.show);
            didsub = itemView.findViewById(R.id.detail_idsub);
            dnamesub = itemView.findViewById(R.id.detail_namesub);

        }
    }

}
