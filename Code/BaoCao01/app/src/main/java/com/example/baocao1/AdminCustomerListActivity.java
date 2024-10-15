package com.example.baocao1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AdminCustomerListActivity extends AppCompatActivity {
    private ImageView icon_back;
    private LinearLayout customerItem;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_khachhang_admin);

        icon_back = findViewById(R.id.icon_back);
        customerItem = findViewById(R.id.customerItem);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminCustomerListActivity.this, AdminActivity.class));
            }
        });

        customerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminCustomerListActivity.this, AdminDetailsAccountActivity.class));
            }
        });
    }
}