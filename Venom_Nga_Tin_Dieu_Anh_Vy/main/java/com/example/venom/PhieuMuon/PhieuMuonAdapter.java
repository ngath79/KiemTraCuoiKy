package com.example.venom.PhieuMuon;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.venom.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Activity activity;
    private int resource;
    private List<PhieuMuon> objects;

    public PhieuMuonAdapter(@NonNull Activity activity, int resource, @NonNull List<PhieuMuon> objects) {
        super(activity, resource, objects);
        this.activity= activity;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //cứ mỗi đối tuuongj sẽ trả về 1 view , với một view tương ứng với đối tượng và nó lưu lại Postion (Vị trí) của sach.
        LayoutInflater inflater =this.activity.getLayoutInflater();
        View view =inflater.inflate(this.resource,null);

        // khai báo 4 text view
        TextView txtmasach = view.findViewById(R.id.tv_text_masach);
        TextView txttensach = view.findViewById(R.id.tv_text_tensach);
        TextView txtngaymuon = view.findViewById(R.id.tv_date_muon);
        TextView txtngaytra = view.findViewById(R.id.tv_date_tra);

        //Lấy đối tượng Phiếu mượn và đưa mã sách tên sách lên textView;
        PhieuMuon phieuMuon =this.objects.get(position);
        txtmasach.setText(phieuMuon.getMasach());
        txttensach.setText(phieuMuon.getTensach());
        txtngaymuon.setText(phieuMuon.getNgaymuon());
        txtngaytra.setText(phieuMuon.getNgaytra());





        Button btn_menu =view.findViewById(R.id.btn_PhieuMuon);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        if (menuItem.getItemId()==R.id.item_add_phieunhap){
//                                Intent intent =new Intent(activity,Them_phieumuon.class);
//                                activity.startActivity(intent);
//                          //  Toast.makeText(getContext(),"Bạn đã chọn thêm phiếu mượn",Toast.LENGTH_SHORT).show();
//                        }
                         if (menuItem.getItemId()==R.id.item_edit_phieunhap){
                            Intent intent =new Intent(activity,SuaPhieuMuonActivity.class);
                            // Gửi phiếu mượn từ fđây qua màn hình cập nhập
                            // ở đây đối số 1: là khóa, dngf để nhận dạng gói rin, gửi qua bên màn hình sửa để lấy đúng chính xác siinh viên thì cần dùng khóa này
                            // đối số 2 : đối tượng phieumuon, cần dùng Implement Serializable để nó có thể lấy đúng chinh xác sinh viên thì cân fdungf
                            intent.putExtra("PHIEUMUON",phieuMuon);
                            activity.startActivity(intent);


                            //     Toast.makeText(getContext(),"Bạn đã chọn thai đổi phiếu:"+phieuMuon.getMasach(),Toast.LENGTH_SHORT).show();
                        }
                        else if (menuItem.getItemId()==R.id.item_del_phieunhap){
                            FirebaseDatabase database =FirebaseDatabase.getInstance();
                            DatabaseReference myRef =database.getReference("DsphieuMuon");
                            myRef.child(phieuMuon.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    //thanh công
                                    Toast.makeText(activity,"Hủy thành công",Toast.LENGTH_LONG).show();
                                }
                            });
                           // Toast.makeText(getContext(),"Bạn đã chọn hủy phiếu:"+phieuMuon.getMasach(),Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                //truywwnf menu.xml vào để nó show lên
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());

                try{
                    Field field =popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu);
                    Class<?> cls =Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method =cls.getDeclaredMethod("setForceShowIcon",new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});
                } catch (Exception e) {
                    Log.d("MYTAG","onClick: " + e.toString());
                }
                popupMenu.show();
            }
        });

        return view;




    }
}
