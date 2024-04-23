package com.ecommercwebsite.aioscrm.ui.todo.ui

import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.databinding.FragmentToDoBinding
import com.ecommercwebsite.aioscrm.ui.todo.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoFragment : FragmentBase<ToDoViewModel, FragmentToDoBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_to_do

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<ToDoViewModel> = ToDoViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}