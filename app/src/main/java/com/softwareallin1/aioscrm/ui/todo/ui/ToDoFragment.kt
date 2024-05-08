package com.softwareallin1.aioscrm.ui.todo.ui

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentToDoBinding
import com.softwareallin1.aioscrm.ui.todo.adapter.ToDoTabsViewPagerAdapter
import com.softwareallin1.aioscrm.ui.todo.viewmodel.ToDoViewModel
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
        viewModel.initVariables()
        setUpViewpager()
    }

    private fun setUpViewpager() {
        val fragmentArrayList: ArrayList<Fragment> = arrayListOf()
        val fragmentNameArrayList: ArrayList<String> = arrayListOf()

        fragmentArrayList.add(UnfinishedToDoFragment())
        fragmentArrayList.add(FinishedToDoFragment())

        fragmentNameArrayList.add("Unfinished(9)")
        fragmentNameArrayList.add("Finished(5)")

        val adapter =
            ToDoTabsViewPagerAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                fragmentArrayList
            )
        getDataBinding().vpToDo.adapter = adapter

        TabLayoutMediator(getDataBinding().tbToDo, getDataBinding().vpToDo) { tab, position ->
            tab.text = fragmentNameArrayList[position]
        }.attach()
    }

    override fun getViewModelClass(): Class<ToDoViewModel> = ToDoViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true

}