package com.softwareallin1.aioscrm.ui.notification.viewmodel

import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {

    fun initVariables() {

    }

}