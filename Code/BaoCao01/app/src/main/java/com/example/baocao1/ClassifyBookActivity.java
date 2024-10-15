package com.example.baocao1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ClassifyBookActivity extends AppCompatActivity {
    private ImageView icon_back, home, author, shoppingCart, userAccount;
    private LinearLayout bookItem, bookItem3, bookItem2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_theloaisach_user);
        icon_back = findViewById(R.id.icon_back);
        bookItem = findViewById(R.id.bookItem);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassifyBookActivity.this, HomeActivity.class));
            }
        });
        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassifyBookActivity.this, DetailsHotBookActivity.class));
            }
        });
    }
}