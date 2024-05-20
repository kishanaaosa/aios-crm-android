package com.softwareallin1.aioscrm.ui.leads.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentProfileDetailBinding
import com.softwareallin1.aioscrm.ui.leads.ui.tabs.ProfileFragment
import com.softwareallin1.aioscrm.ui.leads.viewmodel.ProfileDetailViewModel
import com.softwareallin1.aioscrm.ui.todo.adapter.ProfileTabsViewPagerAdapter
import com.softwareallin1.aioscrm.ui.todo.ui.FinishedToDoFragment
import com.softwareallin1.aioscrm.ui.todo.ui.UnfinishedToDoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDetailFragment : FragmentBase<ProfileDetailViewModel,FragmentProfileDetailBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_profile_detail

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Profile Detail",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
       setUpViewpager()
    }

    private fun setUpViewpager() {

        val fragmentArrayList: ArrayList<Fragment> = arrayListOf()
        val fragmentNameArrayList: ArrayList<String> = arrayListOf()

        fragmentArrayList.add(ProfileFragment())
        fragmentArrayList.add(ProfileFragment())
        fragmentArrayList.add(ProfileFragment())
        fragmentArrayList.add(ProfileFragment())
        fragmentArrayList.add(ProfileFragment())

        fragmentNameArrayList.add("Profile")
        fragmentNameArrayList.add("Proposal")
        fragmentNameArrayList.add("Tasks")
        fragmentNameArrayList.add("Attachment")
        fragmentNameArrayList.add("Reminders")
        
        val adapter =
            ProfileTabsViewPagerAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                fragmentArrayList
            )
        getDataBinding().vpProfileDetail.adapter = adapter

        TabLayoutMediator(getDataBinding().tbProfileDetail, getDataBinding().vpProfileDetail) { tab, position ->
            tab.text = fragmentNameArrayList[position]
        }.attach()

    }

    override fun getViewModelClass(): Class<ProfileDetailViewModel> = ProfileDetailViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true

}