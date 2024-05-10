package com.softwareallin1.aioscrm.ui.notification.ui

import android.view.View
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentNotificationBinding
import com.softwareallin1.aioscrm.databinding.ItemNotificationBinding
import com.softwareallin1.aioscrm.ui.notification.model.NotificationModel
import com.softwareallin1.aioscrm.ui.notification.viewmodel.NotificationViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : FragmentBase<NotificationViewModel, FragmentNotificationBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_notification

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Notifications",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
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
        setUpNotification(list)
    }

    private fun setUpNotification(notificationList: ArrayList<NotificationModel>?) {
        if (notificationList?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvNotification.adapter = object :
                GenericRecyclerViewAdapter<NotificationModel, ItemNotificationBinding>(
                    requireContext(),
                    notificationList
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_notification

                override fun onBindData(
                    model: NotificationModel,
                    position: Int,
                    dataBinding: ItemNotificationBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    if (position != 0) {
                        if (model.date == getItem(position - 1).date) {
                            dataBinding.tvDate.visibility = View.GONE
                        }
                        else{
                            dataBinding.tvDate.visibility = View.VISIBLE
                        }
                    }
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: NotificationModel, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvNotification.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<NotificationViewModel> =
        NotificationViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}