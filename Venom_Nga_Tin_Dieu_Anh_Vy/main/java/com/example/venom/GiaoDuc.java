package com.example.venom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venom.PhieuMuon.Them_phieumuon;
import com.example.venom.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GiaoDuc extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    SachGiaoDucAdapter sachAdapter;
    ArrayList<Sach> sachArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_van_hoc);
        getSupportActionBar().hide();
        ImageView addSach = findViewById(R.id.img_addsach);
        addSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Them_phieumuon.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rvSach);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Sach> options =
                new FirebaseRecyclerOptions.Builder<Sach>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SachGiaoDuc"),Sach.class)
                        .build();
        sachAdapter = new SachGiaoDucAdapter(options);
        recyclerView.setAdapter(sachAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sachAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sachAdapter.stopListening();
    }
}
