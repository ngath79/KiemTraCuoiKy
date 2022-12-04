package com.example.venom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    private EditText edtemail_361, edtpass_361;
    private ImageButton btnLogin_361;
    private TextView tvReister_361, tvForgotPass_361;
    private FirebaseAuth mAuth_361;
    CheckBox cbNhoMK_361, cbHienMK_361;
    SharedPreferences sharedPreferences_361;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth_361 = FirebaseAuth.getInstance();

        edtemail_361 = findViewById(R.id.edt_Email_lg_361);
        edtpass_361 = findViewById(R.id.edt_Pass_lg_361);
        btnLogin_361 = findViewById(R.id.button_Login_361);
        tvReister_361 = findViewById(R.id.btnDK_DN_361);
        tvForgotPass_361 = findViewById(R.id.tv_forgotPass_361);
        cbNhoMK_361 = findViewById(R.id.checkBoxNhoMK_361);
        cbHienMK_361 = findViewById(R.id.checkBoxHienMK_361);

        sharedPreferences_361 = getSharedPreferences("dataLogin", MODE_PRIVATE);

        edtemail_361.setText(sharedPreferences_361.getString("email",""));
        edtpass_361.setText(sharedPreferences_361.getString("password",""));
        cbNhoMK_361.setChecked(sharedPreferences_361.getBoolean("checked", false));

        cbHienMK_361.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edtpass_361.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edtpass_361.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btnLogin_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    login();


            }
        });
        tvReister_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        tvForgotPass_361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Forgot_Pass_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void register() {
        Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
        startActivity(intent);
    }

    private void login() {
        String email = edtemail_361.getText().toString();
        String pass = edtpass_361.getText().toString();
        if(email.isEmpty()||pass.isEmpty()){
            Toast.makeText(Login_Activity.this, "Vui lòng nhập đầy đủ thông tin!!!",Toast.LENGTH_SHORT).show();
        }
        mAuth_361.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    if (cbNhoMK_361.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences_361.edit();
                        editor.putString("email", email);
                        editor.putString("password", pass);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences_361.edit();
                        editor.remove("email");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                    Intent intent = new Intent(Login_Activity.this, Management.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}