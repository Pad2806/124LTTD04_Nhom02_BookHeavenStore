package com.example.baocao1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {
    View view;
    private ImageView icon_noti;
    private LinearLayout hot_item, sachthieunhi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        icon_noti = view.findViewById(R.id.icon_noti);
        hot_item = view.findViewById(R.id.hot_item);
        sachthieunhi = view.findViewById(R.id.sachthieunhi);

        icon_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), NotificationActivity.class));
            }
        });

        hot_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), DetailsHotBookActivity.class));
            }
        });
        sachthieunhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), ClassifyBookActivity.class));
            }
        });
        return view;
    }
}