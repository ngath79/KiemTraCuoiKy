package com.example.venom.staff;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.venom.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffAdapter extends FirebaseRecyclerAdapter<Staff, StaffAdapter.myViewHolder> {

    public StaffAdapter(@NonNull FirebaseRecyclerOptions<Staff> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Staff model) {
        holder.idNV.setText(model.getIdNV());
        holder.tenNV.setText(model.getTenNV());
        holder.chucVu.setText(model.getChucVu());
        holder.namsinh.setText(model.getNamSinh());

        Glide.with(holder.imgAvatar.getContext())
                .load(model.getUrlAnh())
                .placeholder(R.drawable.img)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgAvatar);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgAvatar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_staff))
                        .setExpanded(true,1350)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText maNV = v.findViewById(R.id.edt_idNV);
                EditText tenNV = v.findViewById(R.id.edtNameNV);
                EditText chucVu = v.findViewById(R.id.edt_Chucvu);
                EditText namsinh = v.findViewById(R.id.edtNamsinhNV);
                EditText urlAnh = v.findViewById(R.id.edt_urlAnh);

                Button btnUpdate = v.findViewById(R.id.btnEditNV);
                Button btnCancel = v.findViewById(R.id.btnHuy_EditNV);
                maNV.setText(model.getIdNV());
                tenNV.setText(model.getTenNV());
                chucVu.setText(model.getChucVu());
                namsinh.setText(model.getNamSinh());
                urlAnh.setText(model.getUrlAnh());

                dialogPlus.show();
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("idNhanVien",maNV.getText().toString());
                        map.put("TenNV",tenNV.getText().toString());
                        map.put("ChucVu",chucVu.getText().toString());
                        map.put("NamSinh",namsinh.getText().toString());
                        map.put("urlAnh",urlAnh.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Staff")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.idNV.getContext(), "Update thanh cong ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.idNV.getContext(), "Update  that bai ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.idNV.getContext());
                builder.setTitle("Bạn muốn xóa ");
                builder.setMessage("Không thể hoàn tác ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Staff")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_staff_item,parent,false);
        return new myViewHolder(view);
    }

    class  myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView  imgAvatar;
        Button btnUpdate,btnDelete;
        TextView idNV, tenNV, chucVu, namsinh;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            idNV = itemView.findViewById(R.id.tv_idNV);
            tenNV = itemView.findViewById(R.id.tv_nameNV);
            chucVu = itemView.findViewById(R.id.tv_chucVu);
            namsinh = itemView.findViewById(R.id.tv_namSinh);
        }
    }
}
