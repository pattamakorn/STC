package com.example.studentattendent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class studentcheckAdapter extends RecyclerView.Adapter<studentcheckAdapter.MyViewHolder> {

    Context mcontext;
    List<studentcheck> mfollow;
    View view;
    Dialog myDialogrec;

    public studentcheckAdapter(Context mcontext, List<studentcheck> mfollow) {
        this.mcontext = mcontext;
        this.mfollow = mfollow;
    }

    @NonNull
    @Override
    public studentcheckAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_student_follow,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        myDialogrec = new Dialog(mcontext);
        myDialogrec.setContentView(R.layout.profile);

        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String test = mfollow.get(vHolder.getAdapterPosition()).getFollownamesub();

                TextView ko = myDialogrec.findViewById(R.id.dname);

                ko.setText(test);

                myDialogrec.show();



            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull studentcheckAdapter.MyViewHolder holder, int position) {

        holder.follwsub.setText(mfollow.get(position).getFollowidsub()+" "+mfollow.get(position).getFollownamesub());
        holder.follwteach.setText(mfollow.get(position).getFollownameteach());
        holder.follwclass.setText("ห้องเรียน "+mfollow.get(position).getFollowclassroom());

    }

    @Override
    public int getItemCount() {
        return mfollow.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView follwsub,follwteach,follwclass;
        public CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            follwsub = itemView.findViewById(R.id.follow_subname);
            follwteach = itemView.findViewById(R.id.follow_subteach);
            follwclass = itemView.findViewById(R.id.follow_classroom);
            cardView = itemView.findViewById(R.id.cardfollow);

        }
    }


}
