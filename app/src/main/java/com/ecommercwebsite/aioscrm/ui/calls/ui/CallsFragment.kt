package com.ecommercwebsite.aioscrm.ui.calls.ui

import android.view.View
import androidx.lifecycle.Observer
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.bind.GenericRecyclerViewAdapter
import com.ecommercwebsite.aioscrm.databinding.FragmentCallsBinding
import com.ecommercwebsite.aioscrm.databinding.ItemCallsBinding
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLog
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLogListResponse
import com.ecommercwebsite.aioscrm.ui.calls.viewmodel.CallsViewModel
import com.ecommercwebsite.aioscrm.utils.CommonFunctionHelper
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
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        viewModel.initVariables()
        viewModel.getLeadsWisePhoneCallLog()
        setUpObserver()
        getDataBinding().viewModel = viewModel
    }

    private fun setUpObserver() {
        viewModel.callLigListResponse.observe(viewLifecycleOwner, Observer {
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

                is ResponseHandler.OnSuccessResponse<ResponseData<CallLogListResponse>?> -> {
                    viewModel.showProgressBar(false)
                    setUpCalls(it.response?.data?.callLogList)
                }
            }
        })    }

    private fun setUpCalls(callLogList: ArrayList<CallLog>?) {
        if (callLogList?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvCalls.adapter = object :
                GenericRecyclerViewAdapter<CallLog, ItemCallsBinding>(
                    requireContext(),
                    callLogList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_calls

                override fun onBindData(
                    model: CallLog,
                    position: Int,
                    dataBinding: ItemCallsBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: CallLog, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvCalls.visibility = View.GONE
    }


    override fun getViewModelClass(): Class<CallsViewModel> = CallsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}