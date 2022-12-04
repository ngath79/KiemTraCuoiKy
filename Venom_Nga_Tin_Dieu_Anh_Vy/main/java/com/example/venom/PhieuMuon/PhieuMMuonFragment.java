package com.example.venom.PhieuMuon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.venom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PhieuMMuonFragment extends Fragment {
    private ListView lvPhieuMuon;
    private ArrayList<PhieuMuon> phieuMuonArrayList;
    private PhieuMuonAdapter adapter;
    private FloatingActionButton btn_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_m_muon, container, false);
        lvPhieuMuon =v.findViewById(R.id.lv_PhieuMuon);
        btn_add = v.findViewById(R.id.btn_add);


        //khỏi tạo danh sách Phiếu mượn mẫu
        phieuMuonArrayList =new ArrayList<>();
        GetData();
//        phieuMuonArrayList.add(new PhieuMuon("1","S101","Dế mèn phieu lưu ký","1/12/2022","10/12/2022")) ;
//        phieuMuonArrayList.add(new PhieuMuon("2","S102","Harry Potter","2/12/2022","5/12/2022")) ;
//        phieuMuonArrayList.add(new PhieuMuon("3","S103","Tắt đèn","1/12/2022","11/12/2022")) ;

        //tạo Custom Adapter để gán cho listView
        //Đối số 1: nàm hình hiển thị tại (this)| Đối số 2: là view hiển thị cho từng phiếu mượn| Đối số 3: danh sách phieus mượn( dữ liệu để truyền vào listview)
        adapter =new PhieuMuonAdapter( getActivity(), R.layout.costum_listview_item,phieuMuonArrayList);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getContext(), Them_phieumuon.class);
                startActivity(i);
            }
        });
        lvPhieuMuon.setAdapter(adapter);

        return v;

    }
    //Lấy danh sách Phiếu Mượn từ Firebase Database
    private void GetData(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef =database.getReference("DsphieuMuon");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //xóa dữ liệu trên list view và cập nhập lại
                adapter.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    //convert data qua Phieumuon
                    PhieuMuon phieuMuon =data.getValue(PhieuMuon.class);
                    //thêm phiếu mượn vào listview
                    phieuMuon.setId(data.getKey());
                    adapter.add(phieuMuon);
                    Log.d("MYTAG","onDataChange:"+phieuMuon.getMasach());

                }
                Toast.makeText(getContext(),"Load Data Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Load Data Failed"+ databaseError.toString(),Toast.LENGTH_LONG).show();
                Log.d("MYTAG","onCancelled:"+databaseError.toString());
            }
        });
    }

    }
