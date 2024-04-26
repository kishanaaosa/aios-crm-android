package com.ecommercwebsite.aioscrm.network

import com.ecommercwebsite.aioscrm.BaseApp
import com.ecommercwebsite.aioscrm.BuildConfig
import com.ecommercwebsite.aioscrm.utils.DebugLog
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey.ACCESS_TOKEN
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey.IS_LOGIN
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HttpHandleIntercept : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().headers(getJsonHeader()).build()
        val response: Response?

        response = chain.proceed(request)
        if (response.code == 401) {
            return generateCustomResponse(
                401, "",
                chain.request()
            )!!

        } else if (response.code == 500) {
            return generateCustomResponse(
                500, "",
                chain.request()
            )!!


        }
        response.headers["access-token"]?.let {
            BaseApp.myPreference?.setValueString(
                ACCESS_TOKEN,
                it
            )
        }
      /*  response.headers["client"]?.let { BaseApp.myPreference?.setValueString(CLIENT, it) }
        response.headers["uid"]?.let { BaseApp.myPreference?.setValueString(USER_ID, it) }*/
        return response
    }

    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("Content-Type", "application/json")
        builder.add("Accept", "application/json")
        builder.add("app-version", BuildConfig.VERSION_NAME)

        if (BaseApp.myPreference?.getValueBoolean(IS_LOGIN, false) == true) {
            BaseApp.myPreference?.getValueString(ACCESS_TOKEN, "")
                ?.let { builder.add("access-token", it) }/*    BaseApp.myPreference?.getValueString(CLIENT, "")
                ?.let { builder.add("client", it) }
            BaseApp.myPreference?.getValueString(USER_ID, "")
                ?.let { builder.add("uid", it) }*/
        }

        return builder.build()
    }

    /**
     * generate custom response for exception
     */
    fun generateCustomResponse(code: Int, message: String, request: Request): Response? {
        try {
            val body = getJSONObjectForException(message, code).toString()
                .toResponseBody("application/json".toMediaTypeOrNull())
            return Response.Builder()
                .code(code)
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(body)
                .message(message)
                .build()
        } catch (ex: Exception) {
            DebugLog.print(ex)
            return null
        }

    }

    /**
     * generate JSON object for error case
     */
    private fun getJSONObjectForException(message: String, code: Int): JSONObject {

        try {
            val jsonMainObject = JSONObject()

            val `object` = JSONObject()
            `object`.put("status", false)
            `object`.put("message", message)
            `object`.put("message_code", code)
            `object`.put("status_code", code)

            jsonMainObject.put("meta", `object`)

            val obj = JSONObject()
            obj.put("error", JSONArray().put(message))

            jsonMainObject.put("errors", obj)

            return jsonMainObject
        } catch (e: JSONException) {
            DebugLog.print(e)
            return JSONObject()
        }
    }
}