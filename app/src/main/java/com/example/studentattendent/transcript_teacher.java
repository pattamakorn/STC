package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class transcript_teacher extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<score_teacher> listscore;

    public transcript_teacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transcript_teacher, container, false);

        recyclerView = view.findViewById(R.id.recyclertscore);
        score_teacherAdapter Score_teacherAdapter = new score_teacherAdapter(getContext(),listscore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(Score_teacherAdapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listscore = new ArrayList<>();
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("111","ปัทมากร สายรัตน์","50","25","25","100"));
        listscore.add(new score_teacher("112","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("113","Pattamakorn","45","25","25","95"));
        listscore.add(new score_teacher("114","Pattamakorn","45","25","25","95"));
    }
}
