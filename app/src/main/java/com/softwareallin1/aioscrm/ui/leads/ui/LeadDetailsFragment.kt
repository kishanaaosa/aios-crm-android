package com.softwareallin1.aioscrm.ui.leads.ui

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentLeadDetailsBinding
import com.softwareallin1.aioscrm.ui.leads.adapter.ProfileTabsViewPagerAdapter
import com.softwareallin1.aioscrm.ui.leads.ui.tabs.LeadNotesFragment
import com.softwareallin1.aioscrm.ui.leads.ui.tabs.LeadReminderFragment
import com.softwareallin1.aioscrm.ui.leads.ui.tabs.LeadTasksFragment
import com.softwareallin1.aioscrm.ui.leads.ui.tabs.ProfileFragment
import com.softwareallin1.aioscrm.ui.leads.viewmodel.LeadDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeadDetailsFragment : FragmentBase<LeadDetailsViewModel,FragmentLeadDetailsBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_lead_details

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Lead Details",
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
        fragmentArrayList.add(LeadTasksFragment())
        fragmentArrayList.add(LeadReminderFragment())
        fragmentArrayList.add(LeadNotesFragment())
        fragmentArrayList.add(ProfileFragment())

        fragmentNameArrayList.add("Profile")
        fragmentNameArrayList.add("Task")
        fragmentNameArrayList.add("Reminders")
        fragmentNameArrayList.add("Notes")
        fragmentNameArrayList.add("Activity Logs")
        
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

    override fun getViewModelClass(): Class<LeadDetailsViewModel> = LeadDetailsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true

}