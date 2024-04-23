package com.ecommercwebsite.aioscrm.ui.calls.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentCallsBinding
import com.ecommercwebsite.aioscrm.ui.calls.viewmodel.CallsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallsFragment : FragmentBase<CallsViewModel, FragmentCallsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_calls

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Calls",
                isBackButtonVisible = false,
                isBottomNavVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<CallsViewModel> = CallsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}