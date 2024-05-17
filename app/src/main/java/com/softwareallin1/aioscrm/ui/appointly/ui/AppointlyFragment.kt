package com.softwareallin1.aioscrm.ui.appointly.ui

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentAppointlyBinding
import com.softwareallin1.aioscrm.databinding.ItemAppointlyBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.appointly.model.Appointment
import com.softwareallin1.aioscrm.ui.appointly.model.AppointmentResponse
import com.softwareallin1.aioscrm.ui.appointly.viewmodel.AppointlyViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointlyFragment : FragmentBase<AppointlyViewModel, FragmentAppointlyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_appointly

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Appointy",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        setUpObserver()
        viewModel.getAppointments()
    }

    private fun setUpObserver() {

        getDataBinding().fabAddAppointment.setOnClickListener {
            (activity as MainActivity).navigateToNextScreenThroughDirections(
                AppointlyFragmentDirections.actionAppointlyFragmentToAddAppointmentFragment()
            )
        }

        viewModel.appointmentResponse.observe(viewLifecycleOwner, Observer {
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
                    showNoDataFound()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<AppointmentResponse>?> -> {
                    viewModel.showProgressBar(false)
                    if (it.response?.data?.appointments != null) {
                        setUpAppointments(it.response.data?.appointments)
                    }
                }
            }
        })
    }

    private fun setUpAppointments(list: ArrayList<Appointment>?) {
        viewModel.totalRecords.value = list?.size.toString()

        if (list?.size == 0) {
            showNoDataFound()
        } else {

            getDataBinding().rvAppoints.adapter = object :
                GenericRecyclerViewAdapter<Appointment, ItemAppointlyBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_appointly

                override fun onBindData(
                    model: Appointment,
                    position: Int,
                    dataBinding: ItemAppointlyBinding
                ) {
                    dataBinding.model = model
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Appointment, position: Int) {

                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvAppoints.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<AppointlyViewModel> = AppointlyViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}