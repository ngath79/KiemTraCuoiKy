package com.example.venom.PhieuMuon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.venom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Them_phieumuon extends AppCompatActivity {
    private EditText editMaPM,editTenSach,editNgayMuon,editNgayTra;
    private Button btnThem,btnTrove,btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phieumuon);
        addControls();//khai các control trên giao diện
        addEvents();//khai báo  cá sự kiện btn
    }
    private void addControls() {
        editMaPM =findViewById(R.id.edit_text_maPM);
        editTenSach =findViewById(R.id.edit_text_Tensach);
        editNgayMuon =findViewById(R.id.edit_text_ngaymuon);
        editNgayTra =findViewById(R.id.edit_text_ngaytra);

        btnThem=findViewById(R.id.btn_them);
        btnHuy=findViewById(R.id.btn_huy);
        btnTrove=findViewById(R.id.btn_trove);

    }

    private void addEvents() {
        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//trở về lại màn hình trước đó
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hủy sẽ xáo hết text trên edit
                editMaPM.setText("");
                editTenSach.setText("");
                editNgayMuon.setText("");
                editNgayTra.setText("");
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sự kiện thêm thêm phiếu mượn vào firebase
                //lấy id,masach,tensach,ngaymuon,ngaytra
                String masach =editMaPM.getText().toString();
                String tensach =editTenSach.getText().toString();
                String ngaymuon =editNgayMuon.getText().toString();
                String ngaytra =editNgayTra.getText().toString();
                //ko nhập id vì firebase tự sinh cho mình 1 cái id
                PhieuMuon phieuMuon = new PhieuMuon(masach,tensach,ngaymuon,ngaytra);

                FirebaseDatabase database =FirebaseDatabase.getInstance();
                DatabaseReference myRef =database.getReference("DsphieuMuon");
                //tạo một id ngãu nhiên trên firebase database/Dsphieumuon/
                String id = myRef.push().getKey();
                //dựa vào id này, mình sẽ thêm dữ liệu phiếu vào
                myRef.child(id).setValue(phieuMuon).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //nếu thêm thành công sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm thành công!",Toast.LENGTH_LONG).show();
                        finish();//thoát màn hình thêm, trở về màn hình ds
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //nếu thất bại sẽ nhảy vào đây
                        Toast.makeText(getApplicationContext(),"Thêm nút bại!"+e.toString(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }


}