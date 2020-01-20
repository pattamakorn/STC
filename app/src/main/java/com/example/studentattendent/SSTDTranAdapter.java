package com.example.studentattendent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SSTDTranAdapter extends RecyclerView.Adapter<SSTDTranAdapter.MyViewHolder>{

    Context mcontext;
    List<SSTDTran> mselectstdtran;
    View view;

    public SSTDTranAdapter(Context mcontext, List<SSTDTran> mselectstdtran) {
        this.mcontext = mcontext;
        this.mselectstdtran = mselectstdtran;
    }

    @NonNull
    @Override
    public SSTDTranAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_select_std_trans_of_parent,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        vHolder.cardmystd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(mcontext, "ID "+mselectstdtran.get(vHolder.getAdapterPosition()).getSTeidstd(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (mcontext, show_transcript_parent.class);
                intent.putExtra("Myidstd",mselectstdtran.get(vHolder.getAdapterPosition()).getSTeidstd());
                intent.putExtra("Mynamestd",mselectstdtran.get(vHolder.getAdapterPosition()).getSTenamestd());
                mcontext.startActivity(intent);
            }
        });

        vHolder.steeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(mcontext, "ID "+mselectstdtran.get(vHolder.getAdapterPosition()).getSTeidstd(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (mcontext, show_transcript_parent.class);
                intent.putExtra("Myidstd",mselectstdtran.get(vHolder.getAdapterPosition()).getSTeidstd());
                intent.putExtra("Mynamestd",mselectstdtran.get(vHolder.getAdapterPosition()).getSTenamestd());
                mcontext.startActivity(intent);
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SSTDTranAdapter.MyViewHolder holder, int position) {

        holder.steeenamestd.setText(mselectstdtran.get(position).getSTenamestd());
        holder.steeeclaas.setText(mselectstdtran.get(position).getSTeclass());
        Glide.with(view.getContext()).load(mselectstdtran.get(position).getSTeimagestd()).into(holder.steeprofile);

    }

    @Override
    public int getItemCount() {
        return mselectstdtran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView steeenamestd,steeeclaas;
        private ImageView steeprofile;
        private CardView cardmystd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            steeenamestd = itemView.findViewById(R.id.shownamestdselect);
            steeeclaas = itemView.findViewById(R.id.showclassstdselect);
            steeprofile = itemView.findViewById(R.id.showimagestd);
            cardmystd = itemView.findViewById(R.id.cardmystd);
        }
    }
}
