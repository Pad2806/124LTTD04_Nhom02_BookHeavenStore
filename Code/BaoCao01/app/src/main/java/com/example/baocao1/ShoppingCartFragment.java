package com.example.baocao1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class ShoppingCartFragment extends Fragment {
    View view;
    private ImageView icon_back, icon_backhome;
    private int count = 0;
    private TextView plus, minus, numOrder, btnBuy, btnDel, btnCancel, btnAgree, totalPrice;
    Dialog dialog, dialog1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        icon_back = view.findViewById(R.id.icon_back);
        icon_backhome = view.findViewById(R.id.icon_backhome);
        plus = view.findViewById(R.id.plus);
        minus = view.findViewById(R.id.minus);
        numOrder = view.findViewById(R.id.numOrder);
        btnBuy = view.findViewById(R.id.btnBuy);
        btnDel = view.findViewById(R.id.btnDel);
        totalPrice = view.findViewById(R.id.totalPrice);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.layout_dialog_deleteshoppingcart);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog.setCancelable(false);

        dialog1 = new Dialog(requireContext());
        dialog1.setContentView(R.layout.layout_dialog_delscsuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog1.setCancelable(false);

        btnAgree = dialog.findViewById(R.id.buyAgree);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        icon_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 10) {
                    count++;
                    numOrder.setText(String.valueOf(count));
                    totalPrice.setText(String.valueOf(100000 * count) + " đ");
                }
                UpdateOrderBtn();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count >= 1) {
                    count--;
                    numOrder.setText(String.valueOf(count));
                    totalPrice.setText(String.valueOf(100000 * count) + " đ");
                }
                UpdateOrderBtn();
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PaymentActivity.class));
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShoppingCartFragment.class));
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