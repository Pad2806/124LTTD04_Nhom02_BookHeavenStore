package com.example.baocao1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapterMain(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            HomeFragment()
        else if (position == 1)
            AuthorFragment()
        else if (position == 2)
            ShoppingCartFragment()
        else if (position == 3)
            AccountFragment()
        else
            HomeFragment()
    }

}
