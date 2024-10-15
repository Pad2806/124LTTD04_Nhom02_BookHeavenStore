package com.example.baocao1;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class OrderHistoryActivity extends AppCompatActivity {
    private ImageView icon_back;
    private LinearLayout btnCancelOrder;
    Dialog dialog, dialog1;
    private TextView btnCancel, btnAgree;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dondat_user);

        icon_back = findViewById(R.id.icon_back);
        btnCancelOrder = findViewById(R.id.btnCancel);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_cancelorder);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.layout_dialog_cancelordersuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog1.setCancelable(false);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnAgree = dialog.findViewById(R.id.buyAgree);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
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
                        dialog1.dismiss();
                        startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
                    }
                }, 1000);
            }
        });
    }
}