package com.ecommercwebsite.aioscrm.ui.calls.repository

import com.ecommercwebsite.aioscrm.base.BaseRepository
import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLogListResponse
import javax.inject.Inject

class CallsRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun getLeads(
        staffId: String
    ): ResponseHandler<ResponseData<CallLogListResponse>?> {
        return makeAPICall({
            apiInterface.getLeadsWisePhoneCallLog(staffId)
        })
    }
}