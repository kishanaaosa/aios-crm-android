package com.softwareallin1.aioscrm.ui.attendance.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.attendance.model.FillAttendanceResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class AttendanceRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun fillAttendance(
        staffId: String,
        late: String,
        long: String,
        image: MultipartBody.Part?

        ): ResponseHandler<ResponseData<FillAttendanceResponse>?> {
        return makeAPICall({
            apiInterface.fillAttendance(staffId,late,long,image)
        })
    }
}