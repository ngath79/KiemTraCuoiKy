package com.example.venom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_Activity extends AppCompatActivity {
    private EditText edtemail_361, edtpass_361, edtpass_conf_361;
    private ImageButton btn_signin_361;
    private TextView tvSignup_361;
    private FirebaseAuth mAuth_361;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth_361 = FirebaseAuth.getInstance();

        edtemail_361 = findViewById(R.id.edt_Email_361);
        edtpass_361 = findViewById(R.id.edt_Pass_361);
        edtpass_conf_361 = findViewById(R.id.edt_Pass_ConF_361);
        btn_signin_361 = findViewById(R.id.button_Signin_361);
        tvSignup_361 = findViewById(R.id.signup_OfLIn_361);

        tvSignup_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btn_signin_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String email_361 = edtemail_361.getText().toString();
        String pass_361 = edtpass_361.getText().toString();
        String pass_conf_361 = edtpass_conf_361.getText().toString();
        if(email_361.isEmpty()||pass_361.isEmpty()){
            Toast.makeText(Register_Activity.this, "Vui lòng nhập đầy đủ thông tin!!!",Toast.LENGTH_SHORT).show();
        }else if(!pass_361.equals(pass_conf_361)){
            Toast.makeText(Register_Activity.this, "2 mật khẩu không giống nhau!!!",Toast.LENGTH_SHORT).show();
        }
        mAuth_361.createUserWithEmailAndPassword(email_361,pass_361).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register_Activity.this, Management.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login() {
        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
        startActivity(intent);
    }
}