package com.softwareallin1.aioscrm.base

import android.accounts.NetworkErrorException
import com.softwareallin1.aioscrm.network.ErrorWrapper
import com.softwareallin1.aioscrm.network.HttpCommonMethod
import com.softwareallin1.aioscrm.network.HttpErrorCode
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.utils.Constants.JSON
import com.softwareallin1.aioscrm.utils.DebugLog
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Common class for API calling
 */

open class BaseRepository {

    /**
     * This is the Base suspended method which is used for making the call of an Api and
     * Manage the Response with response code to display specific response message or code.
     * @param call ApiInterface method definition to make a call and get response from generic Area.
     */
    suspend fun <T : Any> makeAPICall(
        call: suspend () -> Response<ResponseData<T>>, isAutoRetry: Boolean = false
    ): ResponseHandler<ResponseData<T>?> {
        var response: Response<ResponseData<T>>? = null
        return withContext(Dispatchers.IO) {
            //emit response
            val res = flow { emit(call.invoke()) }
            //if error response than make api call attempt with delay
            res.retryWhen { cause, attempt ->
                if (cause is Exception && attempt < 3 && isAutoRetry) {
                    delay(2000)
                    return@retryWhen true
                } else {
                    return@retryWhen false
                }
            }
                //catch exception
                .catch {
                    DebugLog.e("Error: ${it.message}")
                }
                //collect response
                .collect {
                    response = it
                    // response = Response.success(it.body()) //convert ResponseData<T> to Response<ResponseData<T>> & set response
                }
            try {
                when {
                    response?.code() in (200..300) -> {
                        return@withContext when (response?.body()?.meta?.statusCode) {
                            400 -> {
                                ResponseHandler.OnFailed(
                                    response?.body()?.meta?.statusCode!!,
                                    response?.body()?.meta?.message!!,
                                    "0"
                                )
                            }

                            401 -> {
                                ResponseHandler.OnFailed(
                                    HttpErrorCode.UNAUTHORIZED.code,
                                    response?.body()?.meta?.message!!,
                                    response?.body()?.meta?.statusCode!!.toString()
                                )
                            }

                            222 -> {
                                ResponseHandler.OnFailed(
                                    HttpErrorCode.NEW_VERSION_FOUND.code,
                                    response?.body()?.meta?.message!!,
                                    response?.body()?.meta?.statusCode!!.toString()
                                )
                            }

                            433 -> {
                                ResponseHandler.OnFailed(
                                    HttpErrorCode.SERVER_UNDER_MAINTENANCE.code,
                                    response?.body()?.meta?.message!!,
                                    response?.body()?.meta?.statusCode!!.toString()
                                )
                            }

                            else -> ResponseHandler.OnSuccessResponse(response?.body())
                        }
                    }

                    response?.code() == 401 -> {
                        return@withContext parseUnAuthorizeResponse(response?.errorBody())
                    }

                    response?.code() == 422 -> {
                        return@withContext parseServerSideErrorResponse(response?.errorBody())
                    }/* response?.code() == 433 -> {
                        return@withContext underMaintenance(response?.errorBody())
                    }*/
                    response?.code() == 500 -> {
                        return@withContext ResponseHandler.OnFailed(
                            HttpErrorCode.NOT_DEFINED.code,
                            "",
                            response?.body()?.meta?.messageCode.toString()
                        )
                    }

                    else -> {
                        return@withContext parseUnKnownStatusCodeResponse(response?.errorBody())
                    }
                }
            } catch (e: Exception) {
                DebugLog.print(e)
                return@withContext when (e) {
                    is UnknownHostException, is ConnectionShutdownException -> {
                        ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, "", "")
                    }

                    is SocketTimeoutException, is IOException, is NetworkErrorException -> {
                        ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
                    }

                    else -> {
                        ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "", "")
                    }
                }
            }
        }
    }

    /* private fun underMaintenance(response: ResponseBody?): ResponseHandler.OnFailed{
         val message: String
         val bodyString = response!!.string()
         val responseWrapper: ErrorWrapper = JSON.readValue(bodyString)
         message = if (responseWrapper.meta!!.statusCode == 401) {
             if (responseWrapper.errors != null) {
                 HttpCommonMethod.getErrorMessage(responseWrapper.errors)
             } else {
                 responseWrapper.meta.message.toString()
             }
         } else {
             responseWrapper.meta.message.toString()
         }
         return ResponseHandler.OnFailed(
             HttpErrorCode.SERVER_UNDER_MAINTENANCE.code,
             message,
             responseWrapper.meta.messageCode.toString()
         )
     }*/

    /**
     * Response parsing for 401 status code
     **/
    private fun parseUnAuthorizeResponse(response: ResponseBody?): ResponseHandler.OnFailed {
        val message: String
        val bodyString = response!!.string()
        val responseWrapper: ErrorWrapper = JSON.readValue(bodyString)
        message = if (responseWrapper.meta!!.statusCode == 401) {
            if (responseWrapper.errors != null) {
                HttpCommonMethod.getErrorMessage(responseWrapper.errors)
            } else {
                responseWrapper.meta.message.toString()
            }
        } else {
            responseWrapper.meta.message.toString()
        }
        return ResponseHandler.OnFailed(
            HttpErrorCode.UNAUTHORIZED.code, message, responseWrapper.meta.messageCode.toString()
        )
    }

    /**
     * Response parsing for 422 status code
     * */
    private fun parseServerSideErrorResponse(response: ResponseBody?): ResponseHandler.OnFailed {
        val message: String
        val bodyString = response?.string()
        val responseWrapper: ErrorWrapper = JSON.readValue(bodyString!!)

        message = if (responseWrapper.meta!!.statusCode == 422) {
            if (responseWrapper.errors != null) {
                HttpCommonMethod.getErrorMessage(responseWrapper.errors)
            } else {
                responseWrapper.meta.message.toString()
            }
        } else {
            responseWrapper.meta.message.toString()
        }
        return ResponseHandler.OnFailed(
            HttpErrorCode.EMPTY_RESPONSE.code, message, responseWrapper.meta.messageCode.toString()
        )
    }

    /**
     * Response parsing for unknown status code
     * */
    private fun parseUnKnownStatusCodeResponse(response: ResponseBody?): ResponseHandler.OnFailed {
        val bodyString = response?.string()
        val responseWrapper: ErrorWrapper = JSON.readValue(bodyString!!)
        val message = if (responseWrapper.meta!!.statusCode == 422) {
            if (responseWrapper.errors != null) {
                HttpCommonMethod.getErrorMessage(responseWrapper.errors)
            } else {
                responseWrapper.meta.message.toString()
            }
        } else {
            responseWrapper.meta.message.toString()
        }
        return if (message.isEmpty()) {
            ResponseHandler.OnFailed(
                HttpErrorCode.EMPTY_RESPONSE.code,
                message,
                responseWrapper.meta.messageCode.toString()
            )
        } else {
            ResponseHandler.OnFailed(
                HttpErrorCode.NOT_DEFINED.code, message, responseWrapper.meta.messageCode.toString()
            )
        }
    }
}