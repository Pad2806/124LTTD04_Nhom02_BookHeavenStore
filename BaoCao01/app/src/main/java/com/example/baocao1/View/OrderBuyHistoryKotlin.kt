package com.example.baocao1.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OrderBuyHistoryKotlin (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            OrderedHistory3011Fragment()
        else if (position == 1)
            BoughtHistoryFragment()
        else
            OrderedHistory3011Fragment()
    }

    override fun getItemCount(): Int {
        return 2;
    }
}