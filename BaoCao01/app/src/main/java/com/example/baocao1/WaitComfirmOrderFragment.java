package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class WaitComfirmOrderFragment extends Fragment {
    private TextView btnConfirm, btnDel, btnAgree, btnCancel;
    Dialog dialog, dialog1, dialog2;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_comfirm_order, container, false);

        btnDel = view.findViewById(R.id.btnDel);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        dialog = new Dialog(requireActivity());
        dialog.setContentView(R.layout.layout_dialog_cancelorder);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        dialog1 = new Dialog(requireActivity());
        dialog1.setContentView(R.layout.layout_dialog_cancelorder_admin);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog1.setCancelable(false);

        dialog2 = new Dialog(requireActivity());
        dialog2.setContentView(R.layout.layout_dialog_confirmorder_admin);
        Objects.requireNonNull(dialog2.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog2.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog2.setCancelable(false);

        btnAgree = dialog.findViewById(R.id.buyAgree);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog2.dismiss();
                    }
                }, 1000);
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
                dialog.dismiss();
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog1.dismiss();
                    }
                }, 1000);
            }
        });
        return view;
    }
}