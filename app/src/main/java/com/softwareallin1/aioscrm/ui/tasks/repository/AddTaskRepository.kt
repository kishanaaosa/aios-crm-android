package com.softwareallin1.aioscrm.ui.tasks.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import javax.inject.Inject

class AddTaskRepository  @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

}