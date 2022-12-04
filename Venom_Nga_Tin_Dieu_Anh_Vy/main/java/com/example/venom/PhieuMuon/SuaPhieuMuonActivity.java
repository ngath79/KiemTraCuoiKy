package com.example.venom.PhieuMuon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.venom.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuaPhieuMuonActivity extends AppCompatActivity {
    private EditText editMaPM,editTenSach,editNgayMuon,editNgayTra;
    private Button btnCapNhap,btnTrove,btnHuy;
    private PhieuMuon phieuMuon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_phieu_muon);
        addControls();//khai các control trên giao diện
        addEvents();//khai báo  cá sự kiện btn
    }

    private void addControls() {
        editMaPM =findViewById(R.id.edit_text_maPM);
        editTenSach =findViewById(R.id.edit_text_Tensach);
        editNgayMuon =findViewById(R.id.edit_text_ngaymuon);
        editNgayTra =findViewById(R.id.edit_text_ngaytra);

        btnCapNhap=findViewById(R.id.btn_capnhap);
        btnHuy=findViewById(R.id.btn_huy);
        btnTrove=findViewById(R.id.btn_trove);

        //Lấy gói tin vừa được gửi tư màn hình ngoài
        Intent intent =getIntent();
        //truyền  khóa vừa nãy vào
        phieuMuon = (PhieuMuon) intent.getSerializableExtra("PHIEUMUON");
        if(phieuMuon!=null){
            //đưa thong tin lên editText
            editMaPM.setText(phieuMuon.getMasach());
            editTenSach.setText(phieuMuon.getTensach());
            editNgayMuon.setText(phieuMuon.getNgaymuon());
            editNgayTra.setText(phieuMuon.getNgaytra());
        }
        else {
            Toast.makeText(this,"Lỗi khi load dữ liệu",Toast.LENGTH_LONG).show();
        }
    }

    private void addEvents(){
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khi người dùng muốn sửa thông tin mà muốn trở lại như cũ thì nhần nút hủy dữ liệu sẽ trở lại
                if(phieuMuon!=null){
                    //đưa thong tin lên editText
                    editMaPM.setText(phieuMuon.getMasach());
                    editTenSach.setText(phieuMuon.getTensach());
                    editNgayMuon.setText(phieuMuon.getNgaymuon());
                    editNgayTra.setText(phieuMuon.getNgaytra());
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lỗi khi load dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cập nhập lấy toannf bộ thông tin trên  edt và update lên firebase
                String masach =editMaPM.getText().toString();
                String tensach =editTenSach.getText().toString();
                String ngaymuon =editNgayMuon.getText().toString();
                String ngaytra =editNgayTra.getText().toString();
                String id =phieuMuon.getId();
                //ở đây chúng ta cần id để firebase tim ftreen CSDL, nêu strungf id thì sẽ dược cập nhập
                FirebaseDatabase database =FirebaseDatabase.getInstance();
                DatabaseReference myRef =database.getReference("DsphieuMuon");
                myRef.child(id).child("masach").setValue(masach);
                myRef.child(id).child("ngaymuon").setValue(ngaymuon);
                myRef.child(id).child("ngaytra").setValue(ngaytra);
                myRef.child(id).child("tensach").setValue(tensach);
                finish();
                //đóng màn chỉnh sửa
                Toast.makeText(getApplicationContext(),"Sửa thành công",Toast.LENGTH_LONG).show();
            }
        });
    }


}