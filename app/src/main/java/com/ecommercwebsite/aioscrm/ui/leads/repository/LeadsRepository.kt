package com.ecommercwebsite.aioscrm.ui.leads.repository

import com.ecommercwebsite.aioscrm.base.BaseRepository
import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.login.model.LoginRequest
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
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