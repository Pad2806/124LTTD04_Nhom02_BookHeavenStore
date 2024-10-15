package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class UpdateInfoProductActivity extends AppCompatActivity {
    private ImageView icon_back;
    Dialog dialog;
    private TextView updateProduct;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cntt_sanpham_admin);

        icon_back = findViewById(R.id.icon_back);
        updateProduct = findViewById(R.id.updateProduct);
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.layout_dialog_updateproductsuccess_admin);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateInfoProductActivity.this, InfoProductsActivity.class));
            }
        });
        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 1000);
            }
        });
    }
}