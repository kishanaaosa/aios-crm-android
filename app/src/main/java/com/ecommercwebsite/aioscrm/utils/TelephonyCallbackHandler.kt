package com.ecommercwebsite.aioscrm.utils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCallbackHandler(val context: Context, private val telephonyManager: TelephonyManager, private val listener: TelephonyCallbackListener) {
    private var telephonyCallback: TelephonyCallback? = null

    fun startListening() {
        telephonyCallback = object : TelephonyCallback(), TelephonyCallback.CallStateListener {
            override fun onCallStateChanged(state: Int) {
                //super.onCallStateChanged(state)
                listener.onCallStateChanged(state)
            }
        }
        telephonyManager.registerTelephonyCallback(context.mainExecutor, telephonyCallback as TelephonyCallback)
    }

    fun stopListening() {
        telephonyCallback?.let {
            telephonyManager.unregisterTelephonyCallback(it)
        }
    }
}

interface TelephonyCallbackListener {
    fun onCallStateChanged(state: Int)
}