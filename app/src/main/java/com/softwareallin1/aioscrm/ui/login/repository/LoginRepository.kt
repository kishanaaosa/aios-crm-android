package com.softwareallin1.aioscrm.ui.login.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.login.model.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun login(
        email: String,
        password: String
    ): ResponseHandler<ResponseData<LoginResponse>?> {
        return makeAPICall({
            apiInterface.login(email, password)
        })
    }
}