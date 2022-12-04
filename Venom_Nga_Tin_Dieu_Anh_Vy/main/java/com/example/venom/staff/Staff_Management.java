package com.example.venom.staff;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.venom.Add_Member;
import com.example.venom.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Staff_Management extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    StaffAdapter staffAdapter;
    ArrayList<Staff> staffArrayList;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staff_management, container, false);

        recyclerView = view.findViewById(R.id.rvStaff);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Staff> options =
                new FirebaseRecyclerOptions.Builder<Staff>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Staff"), Staff.class)
                        .build();
        ImageView addnv = view.findViewById(R.id.img_addnv);
        addnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Add_Staff.class);
                startActivity(intent);
            }
        });
        staffAdapter = new StaffAdapter(options);
        recyclerView.setAdapter(staffAdapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        staffAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        staffAdapter.stopListening();
    }
}

