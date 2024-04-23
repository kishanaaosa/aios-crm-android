package com.ecommercwebsite.aioscrm.ui.appointly.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentAppointlyBinding
import com.ecommercwebsite.aioscrm.ui.appointly.viewmodel.AppointlyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointlyFragment : FragmentBase<AppointlyViewModel, FragmentAppointlyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_appointly

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Appointy",
                isBackButtonVisible = false,
                isBottomNavVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<AppointlyViewModel> = AppointlyViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}