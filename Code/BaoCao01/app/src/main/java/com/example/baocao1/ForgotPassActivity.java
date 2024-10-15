package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ForgotPassActivity extends AppCompatActivity {
    private Button updatePassBtn, agreeBtn;
    private TextView login;
    Dialog dialog;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);
        login = findViewById(R.id.login);
        updatePassBtn = findViewById(R.id.updatePassBtn);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_forgotpass);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogsuccessed_bg));
        dialog.setCancelable(false);

        agreeBtn = dialog.findViewById(R.id.btn_agree);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent(ForgotPassActivity.this, LoginActivity.class)});
            }
        });


        updatePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(ForgotPassActivity.this, LoginActivity.class));
            }
        });
    }
}
