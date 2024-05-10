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

        val statusItems = arrayListOf<String>()
        statusItems.add("New")
        statusItems.add("New")
        statusItems.add("New")
        statusItems.add("New")
        statusItems.add("New")
        val statusAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, statusItems) }
        getDataBinding().actStatus.setAdapter(statusAdapter)

        val sourceItems = arrayListOf<String>()
        sourceItems.add("New")
        sourceItems.add("New")
        sourceItems.add("New")
        sourceItems.add("New")
        sourceItems.add("New")
        val sourceAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, sourceItems) }
        getDataBinding().actSource.setAdapter(sourceAdapter)

        val staffItems = arrayListOf<String>()
        staffItems.add("New")
        staffItems.add("New")
        staffItems.add("New")
        staffItems.add("New")
        staffItems.add("New")
        val staffAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, staffItems) }
        getDataBinding().actStaff.setAdapter(staffAdapter)

        val countryItems = arrayListOf<String>()
        countryItems.add("New")
        countryItems.add("New")
        countryItems.add("New")
        countryItems.add("New")
        countryItems.add("New")
        val countryAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, countryItems) }
        getDataBinding().actCountry.setAdapter(countryAdapter)

    }

    override fun getViewModelClass(): Class<AddLeadViewModel> = AddLeadViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true
}