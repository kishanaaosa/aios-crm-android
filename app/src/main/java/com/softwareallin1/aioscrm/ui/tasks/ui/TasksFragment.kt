package com.softwareallin1.aioscrm.ui.tasks.ui

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentTasksBinding
import com.softwareallin1.aioscrm.databinding.ItemTaskBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
import com.softwareallin1.aioscrm.ui.tasks.model.TaskModel
import com.softwareallin1.aioscrm.ui.leads.ui.LeadsFragmentDirections
import com.softwareallin1.aioscrm.ui.tasks.model.Task
import com.softwareallin1.aioscrm.ui.tasks.model.TaskListResponse
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
        viewModel.getTasks()
        setUpObserver()
    }

    private fun setUpObserver() {
        getDataBinding().fabAddTask.setOnClickListener {
            (activity as MainActivity).navigateToNextScreenThroughDirections(TasksFragmentDirections.actionTasksFragmentToAddTaskFragment())
        }

        viewModel.taskListResponse.observe(viewLifecycleOwner, Observer {
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

                is ResponseHandler.OnSuccessResponse<ResponseData<TaskListResponse>?> -> {
                    viewModel.showProgressBar(false)
                    if (it.response?.data?.taskList != null) {
                        setUpTasks(it.response.data?.taskList)
                    } else {
                        showNoDataFound()
                    }
                }
            }
        })
    }

    private fun setUpTasks(list: ArrayList<Task>?) {
        viewModel.totalRecords.value = list?.size.toString()

        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvTasks.adapter = object :
                GenericRecyclerViewAdapter<Task, ItemTaskBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_task

                override fun onBindData(
                    model: Task,
                    position: Int,
                    dataBinding: ItemTaskBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model

                   /* for (item in model.tagsList?: arrayListOf()) {
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
                    }*/

                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Task, position: Int) {
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