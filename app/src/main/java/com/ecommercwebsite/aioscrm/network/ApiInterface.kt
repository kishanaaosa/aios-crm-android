package com.ecommercwebsite.aioscrm.network

import com.ecommercwebsite.aioscrm.ui.attendance.model.FillAttendanceResponse
import com.ecommercwebsite.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLogListResponse
import com.ecommercwebsite.aioscrm.ui.home.model.CheckAttendanceResponse
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
import retrofit2.http.Query
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
    @POST("staff_attendance_store")
    suspend fun fillAttendance(
        @Query("staffid") staffid: String?,
        @Query("lat") lat: String?,
        @Query("long") long: String?,
        @Part image: MultipartBody.Part?
        ): Response<ResponseData<FillAttendanceResponse>>

    @GET("staff_attendance_get/{staffId}")
    suspend fun checkAttendance(
        @Path("staffId") staffId: String
    ): Response<ResponseData<CheckAttendanceResponse>>

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