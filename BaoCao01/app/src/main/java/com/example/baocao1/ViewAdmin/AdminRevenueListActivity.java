package com.example.baocao1.ViewAdmin;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baocao1.R;

public class AdminRevenueListActivity extends AppCompatActivity {
    private ImageView icon_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doanhthu_admin);

        icon_back = findViewById(R.id.icon_back);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminRevenueListActivity.this, AdminActivity.class));
            }
        });

    }
}