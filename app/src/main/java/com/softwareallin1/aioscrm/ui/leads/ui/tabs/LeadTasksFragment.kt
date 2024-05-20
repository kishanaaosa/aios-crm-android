package com.softwareallin1.aioscrm.ui.leads.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentLeadTasksBinding
import com.softwareallin1.aioscrm.databinding.FragmentProfileBinding
import com.softwareallin1.aioscrm.databinding.ItemNotificationBinding
import com.softwareallin1.aioscrm.databinding.ItemTaskBinding
import com.softwareallin1.aioscrm.ui.leads.viewmodel.LeadDetailsViewModel
import com.softwareallin1.aioscrm.ui.notification.model.NotificationModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeadTasksFragment : FragmentBase<LeadDetailsViewModel, FragmentLeadTasksBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_lead_tasks

    override fun setupToolbar() {  }

    override fun initializeScreenVariables() {
        setupLeadsTasksList()
    }

    fun setupLeadsTasksList() {
        val list: ArrayList<NotificationModel> = arrayListOf()
        list.add(
            NotificationModel(
                "Kishan",
                "Today",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "Yesterday",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Today",
                "Yesterday",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "Yesterday",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "07-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "08-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "09-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "09-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "09-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "10-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )
        list.add(
            NotificationModel(
                "Kishan",
                "10-05-2024",
                "Notification Title",
                "This is the notification description, This is the notification description",
                "10:25PM"
            )
        )

        setupLeadsTasks(list)
    }


    private fun setupLeadsTasks(tasksList: ArrayList<NotificationModel>?) {
        if (tasksList?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvTasks.adapter = object :
                GenericRecyclerViewAdapter<NotificationModel, ItemTaskBinding>(
                    requireContext(),
                    tasksList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_task

                override fun onBindData(
                    model: NotificationModel,
                    position: Int,
                    dataBinding: ItemTaskBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
               
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: NotificationModel, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvTasks.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<LeadDetailsViewModel> = LeadDetailsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true


}
