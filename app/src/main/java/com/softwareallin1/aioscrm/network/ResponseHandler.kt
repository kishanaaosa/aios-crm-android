package com.softwareallin1.aioscrm.network

sealed class ResponseHandler<out T> {
    object Empty : ResponseHandler<Nothing>()
    object Loading : ResponseHandler<Nothing>()
    class OnFailed(val code: Int, val message: String, val messageCode: String) :
        ResponseHandler<Nothing>()

    class OnSuccessResponse<T>(val response: T) : ResponseHandler<T>()
}