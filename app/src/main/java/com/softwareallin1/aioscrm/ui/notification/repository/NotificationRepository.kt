package com.softwareallin1.aioscrm.ui.notification.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.notification.model.NotificationResponse
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val apiInterface: ApiInterface, private val myPreference: MyPreference
) :
    BaseRepository() {
    suspend fun getNotification(): ResponseHandler<ResponseData<NotificationResponse>?> {
        return makeAPICall({
            apiInterface.getNotification(
                myPreference.getValueString(PrefKey.STAFF_ID, "0").toString()
            )
        })
    }
}