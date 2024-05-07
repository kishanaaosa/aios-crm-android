package com.softwareallin1.aioscrm.ui.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentFinishedToDoBinding
import com.softwareallin1.aioscrm.databinding.FragmentUnfinishedToDoBinding
import com.softwareallin1.aioscrm.databinding.ItemFinishedTodoBinding
import com.softwareallin1.aioscrm.databinding.ItemUnfinishedTodoBinding
import com.softwareallin1.aioscrm.ui.todo.model.ToDoModel
import com.softwareallin1.aioscrm.ui.todo.viewmodel.ToDoViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnfinishedToDoFragment : FragmentBase<ToDoViewModel, FragmentUnfinishedToDoBinding>() {
    override fun getLayoutId() = R.layout.fragment_unfinished_to_do

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val todoList: ArrayList<ToDoModel> = arrayListOf()
        todoList.add(ToDoModel("1","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("2","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("3","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("4","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("5","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("6","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("7","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("8","This is the todo Title","10:25 PM 21 Jan 2024",true))
        todoList.add(ToDoModel("9","This is the todo Title","10:25 PM 21 Jan 2024",true))
        setUpToDo(todoList)
    }
    private fun setUpToDo(todoList: ArrayList<ToDoModel>?) {
        if (todoList?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvUnfinishedToDo.adapter = object :
                GenericRecyclerViewAdapter<ToDoModel, ItemUnfinishedTodoBinding>(
                    requireContext(),
                    todoList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_unfinished_todo

                override fun onBindData(
                    model: ToDoModel,
                    position: Int,
                    dataBinding: ItemUnfinishedTodoBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: ToDoModel, position: Int) {
                }
            }
        }
    }
    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvUnfinishedToDo.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<ToDoViewModel> = ToDoViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}