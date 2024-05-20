package com.softwareallin1.aioscrm.ui.myaccount.ui

import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentMyAccountBinding
import com.softwareallin1.aioscrm.ui.myaccount.viewmodel.MyAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : FragmentBase<MyAccountViewModel, FragmentMyAccountBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_my_account

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "My Account",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
    }

    override fun getViewModelClass(): Class<MyAccountViewModel> = MyAccountViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}