package com.example.baocao1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText email, password;
    private TextView register, forgotPass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        register = findViewById(R.id.register);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPass = findViewById(R.id.forgotPass);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("user"))
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                if (email.getText().toString().equals("admin"))
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            }
        });

    }
}
