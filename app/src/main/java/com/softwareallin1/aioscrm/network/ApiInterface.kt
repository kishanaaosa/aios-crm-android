package com.softwareallin1.aioscrm.network

import com.softwareallin1.aioscrm.ui.attendance.model.FillAttendanceResponse
import com.softwareallin1.aioscrm.ui.autocall.model.AddNoteOnLeadResponse
import com.softwareallin1.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.softwareallin1.aioscrm.ui.autocall.model.ChangeLeadStatusResponse
import com.softwareallin1.aioscrm.ui.calls.model.CallLogListResponse
import com.softwareallin1.aioscrm.ui.home.model.CheckAttendanceResponse
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import com.softwareallin1.aioscrm.ui.login.model.LoginResponse
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
    @POST("staff_attendance_store")
    suspend fun fillAttendance(
        @Part("staffid") staffid: String?,
        @Part("lat") lat: String?,
        @Part("long") long: String?,
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

    @FormUrlEncoded
    @POST("lead_status_change_post")
    suspend fun changeLeadStatus(
        @Field("leadid") leadId: String?,
        @Field("status_id") statusId: String?
    ): Response<ResponseData<ChangeLeadStatusResponse>>

    @FormUrlEncoded
    @POST("addNotes_post")
    suspend fun addNotesOnLead(
        @Field("leadid") leadId: String?,
        @Field("description") description: String?,
        @Field("is_from_autocall") isFromAutoCall: Boolean?
    ): Response<ResponseData<AddNoteOnLeadResponse>>
}