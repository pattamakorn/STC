package com.example.studentattendent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.studentattendent.gradestudentAdapter.*;

public class gradestudentAdapter extends RecyclerView.Adapter<gradestudentAdapter.MyViewHolder> {
    Context mcontexttran;
    List<gradestudent> mtran;
    View view;
    Dialog myDialogrec;
    ArrayAdapter<String> arrayAdapter;

    public gradestudentAdapter(Context mcontexttran, List<gradestudent> mtran) {
        this.mcontexttran = mcontexttran;
        this.mtran = mtran;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontexttran).inflate(R.layout.item_student_transcipt,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        myDialogrec = new Dialog(mcontexttran);
        myDialogrec.setContentView(R.layout.detail_item_transcipt);

        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mcontexttran, "KKKKK", Toast.LENGTH_SHORT).show();

            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull gradestudentAdapter.MyViewHolder holder, int position) {

        holder.gradeterm.setText(mtran.get(position).getGradet());
        holder.myterm.setText(mtran.get(position).getTermm());
        holder.myclass.setText(mtran.get(position).getClasss());

    }

    @Override
    public int getItemCount() {
        return mtran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView gradeterm,myclass,myterm;
        public ImageView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myclass = itemView.findViewById(R.id.gradeclass);
            myterm = itemView.findViewById(R.id.gradetermyear);
            gradeterm = itemView.findViewById(R.id.gradeterm);
            cardView = itemView.findViewById(R.id.sdetail);

        }
    }
}
