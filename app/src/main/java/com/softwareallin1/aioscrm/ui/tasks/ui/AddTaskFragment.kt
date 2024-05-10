package com.softwareallin1.aioscrm.ui.tasks.ui

import android.widget.ArrayAdapter
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentAddTaskBinding
import com.softwareallin1.aioscrm.ui.tasks.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : FragmentBase<AddTaskViewModel, FragmentAddTaskBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_add_task

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Add Task",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        setUpAllDropDowns()
    }

    private fun setUpAllDropDowns() {

        val repeatItems = arrayListOf<String>()
        repeatItems.add("New")
        repeatItems.add("New")
        repeatItems.add("New")
        repeatItems.add("New")
        repeatItems.add("New")
        val reapetAdapter = context?.let { ArrayAdapter(it, R.layout.item_spinner, repeatItems) }
        getDataBinding().actRepeat.setAdapter(reapetAdapter)


        val priorityItems = arrayListOf<String>()
        priorityItems.add("New")
        priorityItems.add("New")
        priorityItems.add("New")
        priorityItems.add("New")
        priorityItems.add("New")
        val priorityAdapter =
            context?.let { ArrayAdapter(it, R.layout.item_spinner, priorityItems) }
        getDataBinding().actPriority.setAdapter(priorityAdapter)

    }

    override fun getViewModelClass(): Class<AddTaskViewModel> = AddTaskViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}