package com.example.baocao1.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baocao1.R;
import com.example.baocao1.ViewAdmin.AdminProductListActivity;
import com.example.baocao1.ViewAdmin.UpdateInfoProductActivity;

public class InfoProductsActivity extends AppCompatActivity {
    private ImageView icon_back;
    Dialog dialog;
    private TextView updateProduct;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham_admin);

        icon_back = findViewById(R.id.icon_back);
        updateProduct = findViewById(R.id.updateProduct);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoProductsActivity.this, AdminProductListActivity.class));
            }
        });
        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoProductsActivity.this, UpdateInfoProductActivity.class));
            }
        });

    }
}