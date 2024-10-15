package com.example.baocao1;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BuyHistoryActivity extends AppCompatActivity {
    private ImageView icon_back, icon_cancel;
    private TextView btnRate, btnBuyAgain;
    Dialog dialog;
    private RatingBar ratingBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donmua_user);

        icon_back = findViewById(R.id.icon_back);
        btnBuyAgain = findViewById(R.id.btnBuyAgain);
        btnRate = findViewById(R.id.btnRate);
        btnBuyAgain = findViewById(R.id.btnBuyAgain);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_rating);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_whiterounded));
        dialog.setCancelable(false);

        icon_cancel = dialog.findViewById(R.id.icon_cancel);
        ratingBar = dialog.findViewById(R.id.ratingBar);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBuyAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyHistoryActivity.this, PaymentActivity.class));
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    Toast.makeText(getApplicationContext(), "Bạn đã đánh giá: " + rating + " sao cho đơn hàng", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);
            }
        });

        icon_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyHistoryActivity.this, BuyHistoryActivity.class));
            }
        });
    }
}