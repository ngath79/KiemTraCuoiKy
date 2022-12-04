package com.example.venom.book;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class BookAdapter extends FirebaseRecyclerAdapter<Book, BookAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookAdapter(@NonNull FirebaseRecyclerOptions<Book> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Book model) {
        holder.maSach.setText(model.getIdBook());
        holder.tenSach.setText(model.getNameBook());
        holder.tenTG.setText(model.getActor());
        holder.namXB.setText(model.getYear());

        Glide.with(holder.imgBook.getContext())
                .load(model.getImg())
                .placeholder(R.drawable.img)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgBook);

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgBook.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_book))
                        .setExpanded(true,1350)
                        .create();

                View v = dialogPlus.getHolderView();
                EditText maSach = v.findViewById(R.id.txtMasach);
                EditText tenSach = v.findViewById(R.id.txtTensach);
                EditText tenTG = v.findViewById(R.id.txtActor);
                EditText namXB = v.findViewById(R.id.txtName);
                EditText surl = v.findViewById(R.id.txtImg);

                Button btnUpdate = v.findViewById(R.id.button_addBook);
                Button btnCancel = v.findViewById(R.id.button_cancel);
                maSach.setText(model.getIdBook());
                tenSach.setText(model.getNameBook());
                tenTG.setText(model.getActor());
                namXB.setText(model.getYear());
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
                        map.put("IdBook", maSach.getText().toString());
                        map.put("nameBook",tenSach.getText().toString());
                        map.put("actor",tenTG.getText().toString());
                        map.put("year",namXB.getText().toString());
                        map.put("img",surl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Book")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.maSach.getContext(), "Update thành công ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.maSach.getContext(), "Update  thất bại ", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.maSach.getContext());
                builder.setTitle("Bạn muốn xóa ");
                builder.setMessage("Không thể hoàn tác ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Book")
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
        return null;
    }

    class  myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgBook,imgUpdate,imgDelete;
        TextView maSach, tenSach, tenTG, namXB;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUpdate =  itemView.findViewById(R.id.update_book);
            imgDelete = itemView.findViewById(R.id.delete_book);
            imgBook = (CircleImageView) itemView.findViewById(R.id.imgBook);
            maSach = itemView.findViewById(R.id.tv_masach);
            tenSach = itemView.findViewById(R.id.tv_tensach);
            tenTG = itemView.findViewById(R.id.tv_actor);
            namXB = itemView.findViewById(R.id.tv_year);
        }
    }
}
