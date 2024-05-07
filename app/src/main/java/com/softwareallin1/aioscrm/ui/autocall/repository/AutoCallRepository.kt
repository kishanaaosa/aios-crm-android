package com.softwareallin1.aioscrm.ui.autocall.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.autocall.model.AddNoteOnLeadResponse
import com.softwareallin1.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.softwareallin1.aioscrm.ui.autocall.model.ChangeLeadStatusResponse
import javax.inject.Inject

class AutoCallRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun getAutoCallLeads(
        staffId: String
    ): ResponseHandler<ResponseData<AutoCallLeadsResponse>?> {
        return makeAPICall({
            apiInterface.getAutoCallLeads(staffId)
        })
    }

    suspend fun changeLeadStatus(
        leadId: String?,
        statusId: String?
    ): ResponseHandler<ResponseData<ChangeLeadStatusResponse>?> {
        return makeAPICall({
            apiInterface.changeLeadStatus(leadId, statusId)
        })
    }

    suspend fun addNotesOnLead(
        leadId: String?,
        description: String?
    ): ResponseHandler<ResponseData<AddNoteOnLeadResponse>?> {
        return makeAPICall({
            apiInterface.addNotesOnLead(leadId, description, true)
        })
    }
}