package com.softwareallin1.aioscrm.ui.tasks.ui

import android.view.View
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentTasksBinding
import com.softwareallin1.aioscrm.ui.leads.ui.LeadsFragmentDirections
import com.softwareallin1.aioscrm.ui.tasks.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : FragmentBase<TasksViewModel, FragmentTasksBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tasks

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
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
        showNoDataFound()
        clickListner()
    }

    private fun clickListner() {

        getDataBinding().fabAddTask.setOnClickListener {
            (activity as MainActivity).navigateToNextScreenThroughDirections(TasksFragmentDirections.actionTasksFragmentToAddTaskFragment())
        }

    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvTasks.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<TasksViewModel> = TasksViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}