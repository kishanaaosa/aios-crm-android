package com.softwareallin1.aioscrm.ui.home.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.home.model.CheckAttendanceResponse
import com.softwareallin1.aioscrm.ui.home.model.HomeResponse
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val myPreference: MyPreference
) :
    BaseRepository() {
    suspend fun checkAttendance(): ResponseHandler<ResponseData<CheckAttendanceResponse>?> {
        return makeAPICall({
            apiInterface.checkAttendance(myPreference.getValueString(PrefKey.STAFF_ID, "0").toString())
        })
    }

    suspend fun getHomeData(): ResponseHandler<ResponseData<HomeResponse>?> {
        return makeAPICall({
            apiInterface.getHomeData(myPreference.getValueString(PrefKey.STAFF_ID, "0").toString())
        })
    }
}