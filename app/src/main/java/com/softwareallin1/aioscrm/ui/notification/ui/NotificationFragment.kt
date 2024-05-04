package com.softwareallin1.aioscrm.ui.notification.ui

import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentNotificationBinding
import com.softwareallin1.aioscrm.ui.notification.viewmodel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : FragmentBase<NotificationViewModel, FragmentNotificationBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_notification

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
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
    }

    override fun getViewModelClass(): Class<NotificationViewModel> =
        NotificationViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}