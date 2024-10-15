package com.example.baocao1;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class UserAccountActivity extends AppCompatActivity {
    private ImageView icon_back,home, author, shoppingCart, userAccount;
    private TextView updateInfo, btnCancel, btnAgree;
    private FrameLayout orderHistory, buyHistory, logoutBtn;
    Dialog dialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taikhoan_user);

        icon_back = findViewById(R.id.icon_back);

        updateInfo = findViewById(R.id.updateInfo);
        orderHistory = findViewById(R.id.orderHistory);
        buyHistory = findViewById(R.id.buyHistory);
        logoutBtn = findViewById(R.id.logoutBtn);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_logout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnAgree = dialog.findViewById(R.id.buyAgree);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, HomeActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, HomeActivity.class));
            }
        });
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, AuthorActivity.class));
            }
        });
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, ShoppingCartActivity.class));
            }
        });
        userAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, UserAccountActivity.class));
            }
        });

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, UpdateUserInfoActivity.class));
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, OrderHistoryActivity.class));
            }
        });

        buyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserAccountActivity.this, BuyHistoryActivity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.show();
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
                startActivity(new Intent(UserAccountActivity.this, LoginActivity.class));
            }
        });
    }
}