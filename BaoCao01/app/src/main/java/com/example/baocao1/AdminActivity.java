package com.example.baocao1;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdminActivity extends AppCompatActivity {
    private ImageView icon_logout;
    private LinearLayout hot_item, orderGroup, custumerGroup, productGroup, revenueGroup;
    private TextView buyAgree, btnCancel;
    Dialog dialog;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taikhoan_admin);

        icon_logout = findViewById(R.id.admin_logout);
        hot_item = findViewById(R.id.hot_item);
        orderGroup = findViewById(R.id.orderGroup);
        custumerGroup = findViewById(R.id.customerGroup);
        productGroup = findViewById(R.id.productGroup);
        revenueGroup = findViewById(R.id.revenueGroup);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_logout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        buyAgree = dialog.findViewById(R.id.buyAgree);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        icon_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminActivity.class));
            }
        });

        buyAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });

        hot_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, InfoProductsActivity.class));
            }
        });

        orderGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminOrderListActivity.class));
            }
        });
        productGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminProductListActivity.class));
            }
        });
        revenueGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminRevenueListActivity.class));
            }
        });
        custumerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminCustomerListActivity.class));
            }
        });
    }
}