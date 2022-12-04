package com.example.venom.staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.venom.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Staff extends AppCompatActivity {
    private EditText edtidNV, edtTenNV, edtChucVu, edtNamsinh, edtUrlAnh;
    private Button btnThem, btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_staff);

        initUi();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV = edtidNV.getText().toString().trim();
                String tenNV = edtTenNV.getText().toString().trim();
                String chucVu = edtChucVu.getText().toString().trim();
                String namSinhNV = edtNamsinh.getText().toString().trim();
                String urlAnh = edtUrlAnh.getText().toString().trim();

                Staff staff =new Staff(maNV,tenNV,chucVu,namSinhNV, urlAnh);
                onClickAddStaff(staff);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Staff.this, Staff_Management.class);
                startActivity(intent);
            }
        });
    }
    private  void initUi(){
        edtidNV = findViewById(R.id.edt_idNV);
        edtTenNV = findViewById(R.id.edtNameNV);
        edtChucVu = findViewById(R.id.edt_Chucvu);
        edtNamsinh = findViewById(R.id.edtNamsinhNV);
        edtUrlAnh = findViewById(R.id.edt_urlAnh);
        btnThem = findViewById(R.id.btnAddNV);
        btnHuy = findViewById(R.id.btnHuy_AddNV);
    }
    private void onClickAddStaff(Staff staff){
        FirebaseDatabase  database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Staff");

        String  pathObject = String.valueOf(staff.getIdNV());

        myRef.child(pathObject).setValue(staff, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                Toast.makeText(getApplicationContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}