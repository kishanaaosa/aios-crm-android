package com.ecommercwebsite.aioscrm.network

import com.ecommercwebsite.aioscrm.ui.login.model.LoginRequest
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {
    @POST("login")
    suspend fun login(
        @Body requestOtpRequestBody: LoginRequest
    ): Response<ResponseData<LoginResponse>>
}