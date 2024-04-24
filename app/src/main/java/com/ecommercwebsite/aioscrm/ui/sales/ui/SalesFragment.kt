package com.ecommercwebsite.aioscrm.ui.sales.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentSalesBinding
import com.ecommercwebsite.aioscrm.databinding.FragmentSplashBinding
import com.ecommercwebsite.aioscrm.ui.sales.viewmodel.SalesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalesFragment : FragmentBase<SalesViewModel,FragmentSalesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_sales

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Sales",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}