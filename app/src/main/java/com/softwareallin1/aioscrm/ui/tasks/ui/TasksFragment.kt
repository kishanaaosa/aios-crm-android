package com.softwareallin1.aioscrm.ui.tasks.ui

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentTasksBinding
import com.softwareallin1.aioscrm.databinding.ItemTaskBinding
import com.softwareallin1.aioscrm.ui.tasks.model.TaskModel
import com.softwareallin1.aioscrm.ui.tasks.viewmodel.TasksViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : FragmentBase<TasksViewModel, FragmentTasksBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tasks

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Tasks",
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
        val list: ArrayList<TaskModel> = arrayListOf()
        for (i in 0..100) {
            if (i % 2 == 0) {
                list.add(
                    TaskModel(
                        "High",
                        "Lorem is simply dummy.",
                        "Lorem is simply dummy text of the printing and typesetting industry...",
                        "Pending",
                        arrayListOf("Appointment", " Interview ", "Shaps", "Closed", "maintain"),
                        "2 Feb 2023",
                        "16 Feb 2023"
                    )
                )
            } else {
                list.add(
                    TaskModel(
                        "Low",
                        "Lorem is simply dummy.",
                        "Lorem is simply dummy text of the printing and typesetting industry...",
                        "Completed",
                        arrayListOf("Appointment", " Interview ", "Shaps", "Closed", "maintain"),
                        "2 Feb 2023",
                        "16 Feb 2023"
                    )
                )
            }
        }
        setUpAppointments(list)
        //showNoDataFound()
    }

    private fun setUpAppointments(list: ArrayList<TaskModel>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvTasks.adapter = object :
                GenericRecyclerViewAdapter<TaskModel, ItemTaskBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_task

                override fun onBindData(
                    model: TaskModel,
                    position: Int,
                    dataBinding: ItemTaskBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model

                    for (item in model.tags) {
                        val chip = Chip(requireContext())
                        chip.text = item
                        chip.chipStrokeWidth = resources.getDimension(com.intuit.sdp.R.dimen._1sdp)
                        chip.chipBackgroundColor =
                            ContextCompat.getColorStateList(context, R.color.white)
                        chip.chipStrokeColor = ContextCompat.getColorStateList(
                            context,
                            R.color.colorGradientStartC73B1C
                        )
                        chip.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorGradientStartC73B1C
                            )
                        )

                        chip.chipCornerRadius = resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
                        dataBinding.chipGroup.addView(chip)
                    }

                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: TaskModel, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvTasks.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<TasksViewModel> = TasksViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}