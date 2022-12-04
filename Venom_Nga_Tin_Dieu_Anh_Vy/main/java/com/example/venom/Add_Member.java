package com.example.venom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Member extends AppCompatActivity {
    private EditText edtMatv, edtTen, edtNamsinh,edtSurl;
    private Button btnThem, btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        initUi();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matv = edtMatv.getText().toString().trim();
                String ten = edtTen.getText().toString().trim();
                String namsinh = edtNamsinh.getText().toString().trim();
                String surl = edtSurl.getText().toString().trim();
                Member member =new Member(matv,ten,namsinh,surl);
                onClickAddMember(member);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private  void initUi(){
        edtMatv = findViewById(R.id.txtMatv);
        edtTen = findViewById(R.id.txtName);
        edtNamsinh = findViewById(R.id.txtNamsinh);
        edtSurl = findViewById(R.id.txtImg);
        btnThem = findViewById(R.id.button_addmember);
        btnThoat = findViewById(R.id.button_cancel);
    }
    private void onClickAddMember(Member member){
        FirebaseDatabase  database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Member");

        String  pathObject = String.valueOf(member.getMatv());

        myRef.child(pathObject).setValue(member, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                Toast.makeText(getApplicationContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}