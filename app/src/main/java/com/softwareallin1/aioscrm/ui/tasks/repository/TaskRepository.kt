package com.softwareallin1.aioscrm.ui.tasks.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
import com.softwareallin1.aioscrm.ui.tasks.model.TaskListResponse
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val myPreference: MyPreference
) :
    BaseRepository() {
    suspend fun getStaffList(): ResponseHandler<ResponseData<TaskListResponse>?> {
        return makeAPICall({
            apiInterface.getTasks(myPreference.getValueString(PrefKey.STAFF_ID, "0").toString())
        })
    }
}