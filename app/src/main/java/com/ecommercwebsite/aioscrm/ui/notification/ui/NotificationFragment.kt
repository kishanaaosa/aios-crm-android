package com.ecommercwebsite.aioscrm.ui.notification.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentNotificationBinding
import com.ecommercwebsite.aioscrm.ui.notification.viewmodel.NotificationViewModel
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