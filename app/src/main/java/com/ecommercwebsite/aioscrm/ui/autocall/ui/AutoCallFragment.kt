package com.ecommercwebsite.aioscrm.ui.autocall.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentAutoCallBinding
import com.ecommercwebsite.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoCallFragment : FragmentBase<AutoCallViewModel, FragmentAutoCallBinding>() {
    override fun getLayoutId() = R.layout.fragment_auto_call

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Auto Call",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        viewModel.initVariables()
    }

    override fun getViewModelClass(): Class<AutoCallViewModel> = AutoCallViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}