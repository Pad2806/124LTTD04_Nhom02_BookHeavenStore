package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdminProductListActivity extends AppCompatActivity {
    private ImageView icon_back, icon_addbook;
    private LinearLayout bookItem;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanpham_admin);

        icon_back = findViewById(R.id.icon_back);
        icon_addbook = findViewById(R.id.icon_addbook);
        bookItem = findViewById(R.id.bookItem);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProductListActivity.this, AdminActivity.class));
            }
        });

        icon_addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProductListActivity.this, AddProductActivity.class));
            }
        });

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProductListActivity.this, InfoProductsActivity.class));
            }
        });
    }
}