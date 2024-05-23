package com.softwareallin1.aioscrm.ui.leads.ui.tabs

import android.view.View
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentLeadsNotesBinding
import com.softwareallin1.aioscrm.databinding.ItemNotesBinding
import com.softwareallin1.aioscrm.ui.leads.viewmodel.LeadDetailsViewModel
import com.softwareallin1.aioscrm.ui.notification.model.NotificationModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeadNotesFragment : FragmentBase<LeadDetailsViewModel, FragmentLeadsNotesBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_leads_notes

    override fun setupToolbar() { }

    override fun initializeScreenVariables() {
        setupLeadNotesList()
    }

    fun setupLeadNotesList() {
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

        setupLeadNotes(list)
    }

    private fun setupLeadNotes(tasksList: ArrayList<NotificationModel>?) {
        if (tasksList?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvNotes.adapter = object :
                GenericRecyclerViewAdapter<NotificationModel, ItemNotesBinding>(
                    requireContext(),
                    tasksList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_notes

                override fun onBindData(
                    model: NotificationModel,
                    position: Int,
                    dataBinding: ItemNotesBinding
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
        getDataBinding().rvNotes.visibility = View.GONE
    }


    override fun getViewModelClass(): Class<LeadDetailsViewModel>
    = LeadDetailsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true


}