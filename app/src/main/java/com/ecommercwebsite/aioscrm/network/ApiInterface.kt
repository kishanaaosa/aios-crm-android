package com.ecommercwebsite.aioscrm.network

import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.login.model.LoginRequest
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET("get_leads/{staffId}")
    suspend fun getLeads(
        @Path("staffId") staffId: String
    ): Response<ResponseData<LeadsResponse>>

    @GET("get_lead_details/{leadId}")
    suspend fun getLeadsDetails(
        @Path("leadId") leadId: String
    ): Response<ResponseData<LeadsResponse>>
}