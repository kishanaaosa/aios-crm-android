package com.ecommercwebsite.aioscrm.ui.calls.ui

import android.view.View
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.bind.GenericRecyclerViewAdapter
import com.ecommercwebsite.aioscrm.databinding.FragmentCallsBinding
import com.ecommercwebsite.aioscrm.databinding.ItemCallsBinding
import com.ecommercwebsite.aioscrm.ui.calls.model.CallModel
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
        var callList: ArrayList<CallModel> = arrayListOf()
        callList.add(
            CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
            )
        )
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11"
        ))
        callList.add(CallModel(
            "Hitesh Mehata",
            "9726540727",
            "missedcall",
            "1:03 Min",
            "24-04-2024 3:11PM"
        ))
        setUpCalls(callList)
    }

    private fun setUpCalls(ledaList: ArrayList<CallModel>) {
        if (ledaList.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvCalls.adapter = object :
                GenericRecyclerViewAdapter<CallModel, ItemCallsBinding>(
                    requireContext(),
                    ledaList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_calls

                override fun onBindData(
                    model: CallModel,
                    position: Int,
                    dataBinding: ItemCallsBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: CallModel, position: Int) {

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