package com.ecommercwebsite.aioscrm.ui.todo.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentToDoBinding
import com.ecommercwebsite.aioscrm.ui.todo.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoFragment : FragmentBase<ToDoViewModel, FragmentToDoBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_to_do

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "ToDo",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<ToDoViewModel> = ToDoViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}