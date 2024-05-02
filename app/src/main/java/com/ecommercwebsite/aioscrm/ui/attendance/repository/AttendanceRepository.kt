package com.ecommercwebsite.aioscrm.ui.attendance.repository

import com.ecommercwebsite.aioscrm.base.BaseRepository
import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.attendance.model.FillAttendanceResponse
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