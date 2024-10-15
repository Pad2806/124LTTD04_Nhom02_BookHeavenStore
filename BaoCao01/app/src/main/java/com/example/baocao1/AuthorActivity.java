package com.example.baocao1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorActivity extends AppCompatActivity {
    private ImageView icon_back;
    private LinearLayout author1, author2, author3;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tacgia_user);

        icon_back = findViewById(R.id.icon_back);
        author1 = findViewById(R.id.author1);
        author2 = findViewById(R.id.author2);
        author3 = findViewById(R.id.author3);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        author1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorActivity.this, AuthorsBookActivity.class));
            }
        });
        author2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorActivity.this, AuthorsBookActivity.class));
            }
        });
        author3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorActivity.this, AuthorsBookActivity.class));
            }
        });
    }
}