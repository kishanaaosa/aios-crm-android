package com.ecommercwebsite.aioscrm.ui.login.repository

import com.ecommercwebsite.aioscrm.base.BaseRepository
import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.login.model.LoginRequest
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    suspend fun login(
        loginRequest: LoginRequest
    ): ResponseHandler<ResponseData<LoginResponse>?> {
        return makeAPICall({
            apiInterface.login(loginRequest)
        })
    }
}