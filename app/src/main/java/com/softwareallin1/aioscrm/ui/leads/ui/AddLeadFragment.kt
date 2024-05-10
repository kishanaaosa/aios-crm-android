package com.softwareallin1.aioscrm.ui.leads.ui

import android.widget.ArrayAdapter
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentAddLeadBinding
import com.softwareallin1.aioscrm.ui.leads.viewmodel.AddLeadViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLeadFragment : FragmentBase<AddLeadViewModel, FragmentAddLeadBinding>() {
    override fun getLayoutId() = R.layout.fragment_add_lead

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Add Lead",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        setUpAllDropDowns()
        setUpObserver()
    }

    private fun setUpObserver() {
    }

    private fun setUpAllDropDowns() {
        val items = arrayListOf<String>()
        items.add("New")
        items.add("New")
        items.add("New")
        items.add("New")
        items.add("New")
        val adapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, items) }
        getDataBinding().actStatus.setAdapter(adapter)

    }

    override fun getViewModelClass(): Class<AddLeadViewModel> = AddLeadViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true
}