package com.ecommercwebsite.aioscrm.ui.home.repository

import com.ecommercwebsite.aioscrm.base.BaseRepository
import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.home.model.CheckAttendanceResponse
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