package com.example.baocao1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn, agreeBtn;
    private TextView login;
    Dialog dialog;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangki);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerBtn);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_register);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogsuccessed_bg));
        dialog.setCancelable(false);

        agreeBtn = dialog.findViewById(R.id.btn_agree);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent(RegisterActivity.this, LoginActivity.class)});
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
