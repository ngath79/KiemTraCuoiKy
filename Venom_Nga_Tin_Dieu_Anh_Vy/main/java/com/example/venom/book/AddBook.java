package com.example.venom.book;

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

public class AddBook extends AppCompatActivity {
    private EditText edtMaSach, edtTenSach, edtTenTG, edtNamXB ,edtSurl;
    private Button btnThem, btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initUi();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int IdBook = Integer.parseInt(edtMaSach.getText().toString().trim());
                String nameBook = edtTenSach.getText().toString().trim();
                String actor = edtTenTG.getText().toString().trim();
                String year = edtNamXB.getText().toString().trim();
                int img = Integer.parseInt(edtSurl.getText().toString().trim());
                Book book =new Book(IdBook,nameBook,actor,year,img);
                onClickAddBook(book);
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
        edtMaSach = findViewById(R.id.txtMasach);
        edtTenSach = findViewById(R.id.txtTensach);
        edtTenTG = findViewById(R.id.txtActor);
        edtNamXB = findViewById(R.id.txtName);
        edtSurl = findViewById(R.id.txtImg);
        btnThem = findViewById(R.id.button_addmember);
        btnThoat = findViewById(R.id.button_cancel);
    }
    private void onClickAddBook(Book book){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Book");

        String  pathObject = String.valueOf(book.getIdBook());

        myRef.child(pathObject).setValue(book, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                Toast.makeText(getApplicationContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}