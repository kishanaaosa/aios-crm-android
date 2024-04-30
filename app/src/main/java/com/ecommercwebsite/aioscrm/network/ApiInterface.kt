package com.ecommercwebsite.aioscrm.network

import com.ecommercwebsite.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLogListResponse
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {
    @FormUrlEncoded
    @POST("stafflogin")
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Response<ResponseData<LoginResponse>>

    @Multipart
    @POST("edit-profile-image/{staff_id}")
    suspend fun fillAttendance(
        @Path("staff_id") uuid: String?,
        @Part image: MultipartBody.Part?,
        @Path("lat") lat: String?,
        @Path("long") long: String?,
    ): Response<ResponseData<LoginResponse>>

    @GET("get_leads/{staffId}")
    suspend fun getLeads(
        @Path("staffId") staffId: String
    ): Response<ResponseData<LeadsResponse>>

    @GET("get_lead_details/{leadId}")
    suspend fun getLeadsDetails(
        @Path("leadId") leadId: String
    ): Response<ResponseData<LeadsResponse>>

    @GET("llead_wise_phone_call_log/{leadId}")
    suspend fun getLeadsWisePhoneCallLog(
        @Path("leadId") leadId: String
    ): Response<ResponseData<CallLogListResponse>>

    @GET("get_leads/{staffId}")
    suspend fun getAutoCallLeads(
        @Path("staffId") staffId: String
    ): Response<ResponseData<AutoCallLeadsResponse>>
}