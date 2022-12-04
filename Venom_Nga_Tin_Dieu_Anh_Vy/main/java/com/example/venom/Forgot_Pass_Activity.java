package com.example.venom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Pass_Activity extends AppCompatActivity {
    EditText edt_email_361;
    Button bt_reset_361;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        edt_email_361 = findViewById(R.id.editText_Email_qmk_361);
        bt_reset_361 = findViewById(R.id.button_reset_361);

        firebaseAuth = FirebaseAuth.getInstance();

        bt_reset_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(edt_email_361.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Forgot_Pass_Activity.this,"Mật khẩu đã gửi vào email của bạn", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Forgot_Pass_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}