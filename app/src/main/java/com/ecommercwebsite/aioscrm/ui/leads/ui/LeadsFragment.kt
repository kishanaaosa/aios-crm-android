package com.ecommercwebsite.aioscrm.ui.leads.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentLeadsBinding
import com.ecommercwebsite.aioscrm.ui.leads.viewmodel.LeadsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeadsFragment : FragmentBase<LeadsViewModel, FragmentLeadsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_leads

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Leads",
                isBackButtonVisible = false,
                isBottomNavVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<LeadsViewModel> = LeadsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}