package com.softwareallin1.aioscrm.ui.calls.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.calls.model.CallLogListResponse
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