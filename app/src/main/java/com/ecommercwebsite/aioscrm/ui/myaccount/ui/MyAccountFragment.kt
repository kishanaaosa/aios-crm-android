package com.ecommercwebsite.aioscrm.ui.myaccount.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentMyAccountBinding
import com.ecommercwebsite.aioscrm.ui.myaccount.viewmodel.MyAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : FragmentBase<MyAccountViewModel,FragmentMyAccountBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_my_account

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
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
    }

    override fun getViewModelClass(): Class<MyAccountViewModel> = MyAccountViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}