package com.softwareallin1.aioscrm.ui.home.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.home.model.CheckAttendanceResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun checkAttendance(
        staffId: String,
    ): ResponseHandler<ResponseData<CheckAttendanceResponse>?> {
        return makeAPICall({
            apiInterface.checkAttendance(staffId)
        })
    }
}