package com.example.venom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MemberFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MemberAdapter memberAdapter;
    ArrayList<Member> memberArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);

        //Intent qua giao diện add member
        ImageView addMember = v.findViewById(R.id.img_addmember);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Add_Member.class);
                startActivity(intent);
            }
        });

        //Tạo adapter để gán cho List
        recyclerView = v.findViewById(R.id.rvMember);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Convert data
        FirebaseRecyclerOptions<Member> options =
                new FirebaseRecyclerOptions.Builder<Member>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Member"),Member.class)
                        .build();
        memberAdapter = new MemberAdapter(options);
        recyclerView.setAdapter(memberAdapter);
        return v;


    }
    @Override
    public void onStart() {
        super.onStart();
        memberAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        memberAdapter.stopListening();

    }


}