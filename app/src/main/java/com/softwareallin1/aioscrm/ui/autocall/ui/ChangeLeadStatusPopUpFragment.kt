package com.softwareallin1.aioscrm.ui.autocall.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.DialogFragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentChangeLeadStatusPopUpBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.autocall.model.ChangeLeadStatusResponse
import com.softwareallin1.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeLeadStatusPopUpFragment :
    DialogFragmentBase<AutoCallViewModel, FragmentChangeLeadStatusPopUpBinding>() {
    override fun getLayoutId() = R.layout.fragment_change_lead_status_pop_up

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        setUpObserver()
        getDataBinding().btnChangeStatus.setOnClickListener {
            viewModel.leadStatus.value =
                CommonFunctionHelper.getLeadStatusId(getDataBinding().rgStatus.checkedRadioButtonId)
            viewModel.changeLeadStatus()
        }
    }

    private fun setUpObserver() {
        getDataBinding().btnChangeStatus.setOnClickListener {
            viewModel.leadStatus.value =
                CommonFunctionHelper.getLeadStatusId(getDataBinding().rgStatus.checkedRadioButtonId)
            viewModel.changeLeadStatus()
        }
        viewModel.changeLeadStatusResponse.observe(viewLifecycleOwner, Observer {
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
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<ChangeLeadStatusResponse>?> -> {
                    viewModel.showProgressBar(false)
                    //viewModel.showSnackbarMessage(it.response?.meta?.message.toString())
                    viewModel.isLeadStatusChanged.value = true
                }
            }
        })
        viewModel.isLeadStatusChanged.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.changeLeadStatusResponse.value = ResponseHandler.Empty
                dialog?.dismiss()
            }
        }
    }

    override fun getViewModelClass(): Class<AutoCallViewModel> = AutoCallViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}