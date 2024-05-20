package com.softwareallin1.aioscrm.ui.notification.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.home.model.HomeResponse
import com.softwareallin1.aioscrm.ui.notification.model.NotificationResponse
import com.softwareallin1.aioscrm.ui.notification.repository.NotificationRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val myPreference: MyPreference,
    private val repository: NotificationRepository
) : ViewModelBase() {
    lateinit var notificationResponse: MutableLiveData<ResponseHandler<ResponseData<NotificationResponse>?>>


    fun initVariables() {
        notificationResponse = MutableLiveData<ResponseHandler<ResponseData<NotificationResponse>?>>()

    }

    fun getNotification() {
        viewModelScope.launch {
            notificationResponse.postValue(ResponseHandler.Loading)
            notificationResponse.value = repository.getNotification()
        }
    }

}