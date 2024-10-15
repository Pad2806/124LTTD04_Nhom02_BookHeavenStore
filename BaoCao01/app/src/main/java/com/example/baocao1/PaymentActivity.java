package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {
    private ImageView icon_back, icon_backhome;
    private TextView plus, minus, numOrder, btnAgree, btnBuy, price, totalPrice;
    private int count = 0;
    private LinearLayout payLive, payOnl;
    private CheckBox checkBox, checkBox1;
    Dialog buyDialog;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan_user);

        icon_back = findViewById(R.id.icon_back);
        icon_backhome = findViewById(R.id.icon_backhome);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        numOrder = findViewById(R.id.numOrder);
        checkBox = findViewById(R.id.checkBox);
        checkBox1 = findViewById(R.id.checkBox1);
        payLive = findViewById(R.id.payLive);
        payOnl = findViewById(R.id.payOnl);
        btnBuy = findViewById(R.id.orderBook);
        price = findViewById(R.id.price);
        totalPrice = findViewById(R.id.totalPrice);

        buyDialog = new Dialog(this);
        buyDialog.setContentView(R.layout.layout_dialog_buybook);
        Objects.requireNonNull(buyDialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buyDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        buyDialog.setCancelable(false);

        btnAgree = buyDialog.findViewById(R.id.buyAgree);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, DetailsHotBookActivity.class));
            }
        });
        icon_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, HomeActivity.class));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count < 10){
                    count++;
                    numOrder.setText(String.valueOf(count));
                    price.setText(String.valueOf(100000 * count) + " đ");
                    totalPrice.setText(String.valueOf((100000*count) + 20000 - 15000) + " đ");
                }
                UpdateOrderBtn();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count >= 1){
                    count--;
                    numOrder.setText(String.valueOf(count));
                    price.setText(String.valueOf(100000 * count) + " đ");
                    totalPrice.setText(String.valueOf((100000*count) + 20000 - 15000) + " đ");
                }
                UpdateOrderBtn();
            }
        });
        payLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(true);
                checkBox1.setChecked(false);
            }
        });
        payOnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox1.setChecked(true);
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyDialog.show();
            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, DetailsHotBookActivity.class));
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