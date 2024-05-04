package com.softwareallin1.aioscrm.ui.leads.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import javax.inject.Inject

class LeadsRepository  @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun getLeads(
        staffId: String
    ): ResponseHandler<ResponseData<LeadsResponse>?> {
        return makeAPICall({
            apiInterface.getLeads(staffId)
        })
    }
}