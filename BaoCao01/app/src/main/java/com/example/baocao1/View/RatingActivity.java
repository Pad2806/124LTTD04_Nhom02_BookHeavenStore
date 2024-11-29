package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baocao1.R;

public class RatingActivity extends AppCompatActivity {
    private ImageView icon_back;
    private TextView btnGui;
    Dialog dialog;
    private RatingBar ratingbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgiasanpham);
        icon_back = findViewById(R.id.icon_back);
        btnGui = findViewById(R.id.btnGui);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RatingActivity.this, BuyHistoryActivity.class));
            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(RatingActivity.this, BuyHistoryActivity.class));
                    }
                },1500);

            }
        });
    }
}