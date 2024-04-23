package com.ecommercwebsite.aioscrm.ui.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentTasksBinding
import com.ecommercwebsite.aioscrm.ui.tasks.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : FragmentBase<TasksViewModel,FragmentTasksBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tasks

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Tasks",
                isBackButtonVisible = false,
                isBottomNavVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<TasksViewModel> = TasksViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}