package com.example.baocao1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class AccountFragment extends Fragment {
    private ImageView icon_back,home, author, shoppingCart, userAccount;
    private TextView updateInfo, btnCancel, btnAgree;
    private FrameLayout orderHistory, buyHistory, logoutBtn;
    Dialog dialog;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        icon_back = view.findViewById(R.id.icon_back);

        updateInfo = view.findViewById(R.id.updateInfo);
        orderHistory = view.findViewById(R.id.orderHistory);
        buyHistory = view.findViewById(R.id.buyHistory);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.layout_dialog_logout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog.setCancelable(false);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnAgree = dialog.findViewById(R.id.buyAgree);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdateUserInfoActivity.class));
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OrderHistoryActivity.class));
            }
        });

        buyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BuyHistoryActivity.class));
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
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
}