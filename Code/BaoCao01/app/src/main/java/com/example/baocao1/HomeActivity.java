package com.example.baocao1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager2_Main;
    private TabLayout tabLayout_Main;
    private FragmentPageAdapterMain adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        tabLayout_Main = findViewById(R.id.tabLayout_Main);
        viewPager2_Main = findViewById(R.id.viewPager2_Main);
        adapter = new FragmentPageAdapterMain(getSupportFragmentManager(), getLifecycle());
        viewPager2_Main.setAdapter(adapter);

        tabLayout_Main.addTab(tabLayout_Main.newTab().setIcon(R.drawable.icon_home_60));
        tabLayout_Main.addTab(tabLayout_Main.newTab().setIcon(R.drawable.icon_quill_with_ink_60));
        tabLayout_Main.addTab(tabLayout_Main.newTab().setIcon(R.drawable.icon_shopping_cart_100));
        tabLayout_Main.addTab(tabLayout_Main.newTab().setIcon(R.drawable.icon_account_100));

        tabLayout_Main.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab != null)
                    viewPager2_Main.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2_Main.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout_Main.selectTab(tabLayout_Main.getTabAt(position));
            }
        });
    }
}
