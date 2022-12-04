package com.example.venom;

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

public class SachVanHocAdapter extends FirebaseRecyclerAdapter<Sach, SachVanHocAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SachVanHocAdapter(@NonNull FirebaseRecyclerOptions<Sach> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SachVanHocAdapter.myViewHolder holder, int position, @NonNull Sach model) {
        holder.matv.setText(model.getMasach());
        holder.ten.setText(model.getTen());
        holder.namsinh.setText(model.getNamsx());
        holder.tacgia.setText(model.getTacgia());

        Glide.with(holder.imgAvatar.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.img)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgAvatar);

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgAvatar.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_update_sach))
                        .setExpanded(true,1350)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText matv = v.findViewById(R.id.txtMatv);
                EditText ten = v.findViewById(R.id.txtName);
                EditText namsinh = v.findViewById(R.id.txtNamsinh);
                EditText tacgia = v.findViewById(R.id.txtTacGia);
                EditText surl = v.findViewById(R.id.txtImg);

                Button btnUpdate = v.findViewById(R.id.button_updatemember);
                Button btnCancel = v.findViewById(R.id.button_cancel);
                matv.setText(model.getMasach());
                ten.setText(model.getTen());
                tacgia.setText(model.getTacgia());
                namsinh.setText(model.getNamsx());
                surl.setText(model.getImg());

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
                        map.put("matv",matv.getText().toString());
                        map.put("ten",ten.getText().toString());
                        map.put("namsinh",namsinh.getText().toString());
                        map.put("surl",surl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("SachVanHoc")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.matv.getContext(), "Update thanh cong ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.matv.getContext(), "Update  that bai ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.matv.getContext());
                builder.setTitle("Bạn muốn xóa ");
                builder.setMessage("Không thể hoàn tác ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("SachVanHoc")
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sach,parent,false);
        return new myViewHolder(view);
    }

    class  myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgAvatar,imgUpdate,imgDelete;
        TextView matv, ten, namsinh, tacgia;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUpdate =  itemView.findViewById(R.id.update_member);
            imgDelete = itemView.findViewById(R.id.delete_member);

            imgAvatar = (CircleImageView) itemView.findViewById(R.id.imgAvatar);
            matv = itemView.findViewById(R.id.tv_matv);
            ten = itemView.findViewById(R.id.tv_hoten);
            namsinh = itemView.findViewById(R.id.tv_namsinh);
            tacgia = itemView.findViewById(R.id.tv_tacgia);
        }
    }
}
