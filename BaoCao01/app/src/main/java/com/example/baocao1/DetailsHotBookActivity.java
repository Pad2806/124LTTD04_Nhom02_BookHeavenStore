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

public class DetailsHotBookActivity extends AppCompatActivity {
    private ImageView icon_back, icon_cancel;
    private TextView plus, minus, numOrder, btnBuyBook, btnOrderBook, btnadd, price;
    private int count = 0;
    Dialog dialog, dialog1;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsach_user);

        icon_back = findViewById(R.id.icon_back);
        btnBuyBook = findViewById(R.id.btnBuyBook);
        btnOrderBook = findViewById(R.id.btnOrderBook);


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_shoppingcart);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogorder_bg));
        dialog.setCancelable(false);

        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.layout_dialog_addshoppingcartsuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog1.setCancelable(false);
        price = dialog.findViewById(R.id.price);
        plus = dialog.findViewById(R.id.plus);
        minus = dialog.findViewById(R.id.minus);
        numOrder = dialog.findViewById(R.id.numOrder);
        btnadd = dialog.findViewById(R.id.btn_add);
        icon_cancel = dialog.findViewById(R.id.icon_cancel);


        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsHotBookActivity.this, HomeActivity.class));
            }
        });

        btnOrderBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsHotBookActivity.this, PaymentActivity.class));
            }
        });
        icon_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
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
                }
                UpdateOrderBtn();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count > 1){
                    count--;
                    numOrder.setText(String.valueOf(count));
                    price.setText(String.valueOf(100000 * count) + " đ");
                }else {
                    count = 1;
                    numOrder.setText(String.valueOf(count));
                    price.setText("100000 đ");
                }
                UpdateOrderBtn();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog1.dismiss();
                        startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
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
