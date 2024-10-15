package com.example.baocao1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorsBookActivity extends AppCompatActivity {
    private  ImageView icon_back, home, author, shoppingCart, userAccount;
    private LinearLayout authorBook1,authorBook2,authorBook3;
    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettacgia_user);

        home = findViewById(R.id.home);
        author = findViewById(R.id.author);
        shoppingCart = findViewById(R.id.shoppingCart);
        userAccount = findViewById(R.id.userAccount);
        icon_back = findViewById(R.id.icon_back);
        authorBook1 = findViewById(R.id.authorBook1);
        authorBook2 = findViewById(R.id.authorBook2);
        authorBook3 = findViewById(R.id.authorBook3);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, AuthorActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, HomeActivity.class));
            }
        });
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, AuthorActivity.class));
            }
        });
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, ShoppingCartActivity.class));
            }
        });
        userAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, UserAccountActivity.class));
            }
        });

        authorBook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, DetailsHotBookActivity.class));
            }
        });
        authorBook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, DetailsHotBookActivity.class));
            }
        });
        authorBook3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthorsBookActivity.this, DetailsHotBookActivity.class));
            }
        });
    }
}