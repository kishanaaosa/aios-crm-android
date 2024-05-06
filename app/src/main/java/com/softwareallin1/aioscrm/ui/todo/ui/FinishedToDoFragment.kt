package com.softwareallin1.aioscrm.ui.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentFinishedToDoBinding
import com.softwareallin1.aioscrm.ui.todo.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishedToDoFragment : FragmentBase<ToDoViewModel,FragmentFinishedToDoBinding>() {
    override fun getLayoutId() = R.layout.fragment_finished_to_do

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<ToDoViewModel> = ToDoViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}