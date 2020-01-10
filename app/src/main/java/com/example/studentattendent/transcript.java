package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class transcript extends Fragment {
    View v;

    private TextView grade,credit;
    private RecyclerView recyclerView;
    private List<gradestudent> listgrade;


    public transcript() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_transcript, container, false);

        recyclerView = v.findViewById(R.id.recyclertranscript);
        grade = v.findViewById(R.id.sumgrade);
        credit = v.findViewById(R.id.sumcredit);

        grade.setText("3.59");
        credit.setText("150");

        gradestudentAdapter GradestudentAdapter = new gradestudentAdapter(getContext(),listgrade);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(GradestudentAdapter);



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listgrade = new ArrayList<>();
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 1/2560","ชั้นมัธยมศึกษาปีที่ 4/1"));
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 2/2560","ชั้นมัธยมศึกษาปีที่ 4/1"));
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 1/2561","ชั้นมัธยมศึกษาปีที่ 5/1"));
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 2/2561","ชั้นมัธยมศึกษาปีที่ 5/1"));
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 2/2562","ชั้นมัธยมศึกษาปีที่ 6/1"));
        listgrade.add(new gradestudent("4.00","ภาคเรียนที่ 2/2562","ชั้นมัธยมศึกษาปีที่ 6/1"));

    }
}
