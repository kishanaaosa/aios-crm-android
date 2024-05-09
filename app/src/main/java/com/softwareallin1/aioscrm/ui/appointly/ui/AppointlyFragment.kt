package com.softwareallin1.aioscrm.ui.appointly.ui

import android.view.View
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentAppointlyBinding
import com.softwareallin1.aioscrm.databinding.ItemAppointlyBinding
import com.softwareallin1.aioscrm.ui.appointly.model.AppointmentModel
import com.softwareallin1.aioscrm.ui.appointly.viewmodel.AppointlyViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointlyFragment : FragmentBase<AppointlyViewModel, FragmentAppointlyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_appointly

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
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
        val list: ArrayList<AppointmentModel> = arrayListOf()

        for (i in 0..100) {
            if (i%2==0) {
                list.add(
                    AppointmentModel(
                        "2 Feb 2023",
                        "10:45",
                        "Missed",
                        "Appointment for sales executive Team",
                        "Lorem is simply dummy text of   industry and comment come with other document in that time",
                        "Jhon Came",
                        "Newspapper"

                    )
                )
            }else{
                list.add(
                    AppointmentModel(
                        "2 Feb 2023",
                        "10:45",
                        "Pending",
                        "Appointment for sales executive Team",
                        "Lorem is simply dummy text of   industry and comment come with other document in that time",
                        "Jhon Came",
                        "Newspapper"

                    )
                )
            }
        }

        setUpAppointments(list)
    }

    private fun setUpAppointments(list: ArrayList<AppointmentModel>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvAppoints.adapter = object :
                GenericRecyclerViewAdapter<AppointmentModel, ItemAppointlyBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_appointly

                override fun onBindData(
                    model: AppointmentModel,
                    position: Int,
                    dataBinding: ItemAppointlyBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: AppointmentModel, position: Int) {
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