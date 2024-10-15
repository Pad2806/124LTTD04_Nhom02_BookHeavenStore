package com.example.baocao1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AuthorFragment extends Fragment {
    private ImageView icon_back;
    private LinearLayout author1, author2, author3;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_author, container, false);
        icon_back = view.findViewById(R.id.icon_back);
        author1 = view.findViewById(R.id.author1);
        author2 = view.findViewById(R.id.author2);
        author3 = view.findViewById(R.id.author3);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        author1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AuthorsBookActivity.class));
            }
        });
        author2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AuthorsBookActivity.class));
            }
        });
        author3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AuthorsBookActivity.class));
            }
        });
        return view;
    }
}
