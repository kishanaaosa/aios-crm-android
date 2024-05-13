package com.softwareallin1.aioscrm.ui.leads.ui

import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentAddLeadBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.Staff
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
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
        viewModel.initVariables()
        setUpAllDropDowns()
        setUpObserver()
        viewModel.getLeads()
    }

    private fun setUpObserver() {
        viewModel.staffListResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Empty -> {

                }

                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                    //showNoDataFound()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<StaffListResponse>?> -> {
                    viewModel.showProgressBar(false)
                    if (it.response?.data?.staffList != null) {
                        setUpStaff(it.response.data?.staffList ?: arrayListOf())
                    }
                }
            }
        })
    }

    private fun setUpStaff(staffList: ArrayList<Staff>) {
        val staffItems = arrayListOf<String>()
        staffList.forEach {
            staffItems.add(it.firstname + " " + it.lastname)
        }
        val staffAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, staffItems) }
        getDataBinding().actStaff.setAdapter(staffAdapter)
    }

    private fun setUpAllDropDowns() {

        val statusItems = arrayListOf<String>()
        statusItems.add("New")
        statusItems.add("Not Reachable")
        statusItems.add("Not Interested")
        statusItems.add("Not Received")
        statusItems.add("Intrested")
        statusItems.add("Busy Now")
        statusItems.add("Future Purchase")
        statusItems.add("Other Service")
       // statusItems.add("Other Service")
        val statusAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, statusItems) }
        getDataBinding().actStatus.setAdapter(statusAdapter)

        val sourceItems = arrayListOf<String>()
        sourceItems.add("Facebook")
        sourceItems.add("Google")
        sourceItems.add("Linkdin")
        sourceItems.add("Sheets")
        sourceItems.add("TMS")
        val sourceAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, sourceItems) }
        getDataBinding().actSource.setAdapter(sourceAdapter)

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