package com.softwareallin1.aioscrm.ui.appointly.ui

import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentAppointlyBinding
import com.softwareallin1.aioscrm.ui.appointly.viewmodel.AppointlyViewModel
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
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<AppointlyViewModel> = AppointlyViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}