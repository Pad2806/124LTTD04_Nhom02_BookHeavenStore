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

public class ShoppingCartActivity extends AppCompatActivity {
    private ImageView icon_back, icon_backhome;
    private int count = 0;
    private TextView plus, minus, numOrder, btnBuy, btnDel, btnCancel, btnAgree, totalPrice;
    Dialog dialog, dialog1;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang_user);

        icon_back = findViewById(R.id.icon_back);
        icon_backhome = findViewById(R.id.icon_backhome);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        numOrder = findViewById(R.id.numOrder);
        btnBuy = findViewById(R.id.btnBuy);
        btnDel = findViewById(R.id.btnDel);
        totalPrice = findViewById(R.id.totalPrice);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_deleteshoppingcart);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog.setCancelable(false);

        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.layout_dialog_delscsuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog1.setCancelable(false);

        btnAgree = dialog.findViewById(R.id.buyAgree);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        icon_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this, HomeActivity.class));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < 10){
                    count++;
                    numOrder.setText(String.valueOf(count));
                    totalPrice.setText(String.valueOf(100000 * count) + " đ");
                }
                UpdateOrderBtn();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count >= 1){
                    count--;
                    numOrder.setText(String.valueOf(count));
                    totalPrice.setText(String.valueOf(100000 * count) + " đ");
                }
                UpdateOrderBtn();
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this, PaymentActivity.class));
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class));
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
                        startActivity(new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class));
                    }
                }, 1000);
            }
        });
    }
    private void UpdateOrderBtn() {
        numOrder.setText(String.valueOf(count));

        if (count <= 1) {
            minus.setAlpha(0.5f);
            minus.setEnabled(false);
        } else {
            minus.setAlpha(1.0f);
            minus.setEnabled(true);
        }

        if (count >= 10) {
            plus.setAlpha(0.5f);
            plus.setEnabled(false);
        } else {
            plus.setAlpha(1.0f);
            plus.setEnabled(true);
        }
    }
}