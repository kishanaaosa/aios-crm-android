package com.softwareallin1.aioscrm.ui.settings.ui

import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentSettingsBinding
import com.softwareallin1.aioscrm.ui.settings.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : FragmentBase<SettingsViewModel, FragmentSettingsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings
    override fun setupToolbar() {
        viewModel.initVariables()
        getDataBinding().viewModel = viewModel
    }

    override fun initializeScreenVariables() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Settings",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun getViewModelClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}