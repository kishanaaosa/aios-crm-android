package com.ecommercwebsite.aioscrm.ui.leads.ui

import android.view.View
import androidx.lifecycle.Observer
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.bind.GenericRecyclerViewAdapter
import com.ecommercwebsite.aioscrm.databinding.FragmentLeadsBinding
import com.ecommercwebsite.aioscrm.databinding.ItemLeadsBinding
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.leads.model.Data
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.leads.viewmodel.LeadsViewModel
import com.ecommercwebsite.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeadsFragment : FragmentBase<LeadsViewModel, FragmentLeadsBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_leads

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Leads",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        viewModel.initVariables()
        viewModel.getLeads()
        setUpObserver()
        getDataBinding().viewModel = viewModel
        /* var leadsList: ArrayList<LeadsModel> = arrayListOf()
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))
         leadsList.add(
             LeadsModel(
                 "1", "Sonu Gurnani",
                 "SkyLine Automobiles",
                 "sonu@gmail.com",
                 "9726540727",
                 "Sheets",
                 "New",
                 "Sheets",
                 "0",
                 "0"
             ))


         setUpLeads(leadsList)*/
    }

    private fun setUpObserver() {
        viewModel.leadsResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                    showNoDataFound()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<LeadsResponse>?> -> {
                    viewModel.showProgressBar(false)
                    setUpLeads(it.response?.data?.leads)
                }
            }
        })
    }

    private fun setUpLeads(leads: ArrayList<Data>?) {
        if (leads?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvLeads.adapter = object :
                GenericRecyclerViewAdapter<Data, ItemLeadsBinding>(
                    requireContext(),
                    leads
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_leads

                override fun onBindData(
                    model: Data,
                    position: Int,
                    dataBinding: ItemLeadsBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Data, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvLeads.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<LeadsViewModel> = LeadsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}