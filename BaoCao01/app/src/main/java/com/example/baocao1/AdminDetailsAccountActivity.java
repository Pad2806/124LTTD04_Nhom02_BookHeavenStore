package com.example.baocao1;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdminDetailsAccountActivity extends AppCompatActivity {
    private ImageView icon_back;
    private LinearLayout lockAccount, customerOrderList;
    private TextView btnCancel, btnAgree;
    Dialog dialog, dialog1;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietkhachhang_admin);

        icon_back = findViewById(R.id.icon_back);
        lockAccount = findViewById(R.id.lockAccount);
        customerOrderList = findViewById(R.id.customerOrderList);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_delaccount_admin);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.layout_dialog_delaccountsuccess_admin);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog1.setCancelable(false);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnAgree = dialog.findViewById(R.id.btnAgree);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDetailsAccountActivity.this, AdminCustomerListActivity.class));
            }
        });

        lockAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        customerOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDetailsAccountActivity.this, AdminCustomerOrderListActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(AdminDetailsAccountActivity.this, AdminCustomerListActivity.class));
                        dialog1.dismiss();

                    }
                }, 1000);
            }
        });

    }
}