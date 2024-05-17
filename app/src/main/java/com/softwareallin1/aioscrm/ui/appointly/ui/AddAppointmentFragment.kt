package com.softwareallin1.aioscrm.ui.appointly.ui

import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentAddAppointmentBinding
import com.softwareallin1.aioscrm.ui.appointly.viewmodel.AddAppointmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAppointmentFragment : FragmentBase<AddAppointmentViewModel,FragmentAddAppointmentBinding>(){
    override fun getLayoutId(): Int = R.layout.fragment_add_appointment

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Add Appointment",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
    }

    override fun getViewModelClass(): Class<AddAppointmentViewModel> = AddAppointmentViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}