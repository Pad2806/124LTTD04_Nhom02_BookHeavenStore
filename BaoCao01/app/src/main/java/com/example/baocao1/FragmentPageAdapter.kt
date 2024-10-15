package com.example.baocao1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            WaitComfirmOrderFragment()
        else if (position == 1)
            DeliveringOrdersFragment()
        else if (position == 2)
            DeliveredOrdersFragment()
        else
            WaitComfirmOrderFragment()
    }

}
