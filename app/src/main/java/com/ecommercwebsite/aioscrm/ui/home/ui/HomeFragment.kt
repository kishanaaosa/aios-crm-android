package com.ecommercwebsite.aioscrm.ui.home.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentHomeBinding
import com.ecommercwebsite.aioscrm.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBase<HomeViewModel, FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Home",
                isBackButtonVisible = false,
                isBottomNavVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
    }


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}
