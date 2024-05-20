package com.softwareallin1.aioscrm.ui.todo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileTabsViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragmentArrayList: ArrayList<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragmentArrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return fragmentArrayList[0]
            1 -> return fragmentArrayList[1]
            else -> {
                return fragmentArrayList[0]
            }
        }
    }
}