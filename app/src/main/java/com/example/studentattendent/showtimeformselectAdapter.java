package com.example.studentattendent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class showtimeformselectAdapter extends RecyclerView.Adapter<showtimeformselectAdapter.MyViewHolder> {

    Context mcontext;
    List<showtimeformselect> mselectstdttime;
    View view;

    public showtimeformselectAdapter(Context mcontext, List<showtimeformselect> mselectstdttime) {
        this.mcontext = mcontext;
        this.mselectstdttime = mselectstdttime;
    }

    @NonNull
    @Override
    public showtimeformselectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_select_timestd,parent,false);
        MyViewHolder vHolder = new MyViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull showtimeformselectAdapter.MyViewHolder holder, int position) {
        holder.stieenamestd.setText(mselectstdttime.get(position).getSTinamestd());
        holder.stieeclaas.setText(mselectstdttime.get(position).getSTiclass());
        Glide.with(view.getContext()).load(mselectstdttime.get(position).getSTiimagestd()).into(holder.stieprofile);

    }

    @Override
    public int getItemCount() {
        return mselectstdttime.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView stieenamestd,stieeclaas;
        private ImageView stieprofile;
        private CardView sticardmystd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stieenamestd = itemView.findViewById(R.id.shownamestdselecttime);
            stieeclaas = itemView.findViewById(R.id.showclassstdselecttime);
            stieprofile = itemView.findViewById(R.id.showimagestdtime);
        }
    }
}
