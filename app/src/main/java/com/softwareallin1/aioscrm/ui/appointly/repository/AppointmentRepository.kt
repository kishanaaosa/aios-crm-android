package com.softwareallin1.aioscrm.ui.appointly.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.appointly.model.AppointmentResponse
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
import com.softwareallin1.aioscrm.ui.tasks.model.TaskListResponse
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import javax.inject.Inject


class AppointmentRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val myPreference: MyPreference
) :
    BaseRepository() {
    suspend fun getAppointments(): ResponseHandler<ResponseData<AppointmentResponse>?> {
        return makeAPICall({
            apiInterface.getAppointments(
                myPreference.getValueString(PrefKey.STAFF_ID, "0").toString()
            )
        })
    }
}