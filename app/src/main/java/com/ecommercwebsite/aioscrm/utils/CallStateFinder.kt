package com.ecommercwebsite.aioscrm.utils

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.S)
open class CallStateFinder(var context: Context, var commonCallStateFinder: CommonCallStateFinder)
    :TelephonyCallbackListener,PhoneStateListenerListener{
    private lateinit var telephonyManager: TelephonyManager
    private lateinit var telephonyCallbackHandler: TelephonyCallbackHandler
    private lateinit var phoneStateListenerHandler: PhoneStateListenerHandler

    init {
        telephonyManager = context.getSystemService(TelephonyManager::class.java)
        telephonyCallbackHandler = TelephonyCallbackHandler(context,telephonyManager, this)
        phoneStateListenerHandler = PhoneStateListenerHandler(telephonyManager, this)
    }

    override fun onCallStateChanged(state: Int) {
        commonCallStateFinder.onCallStateChanged(state)
    }

    fun startCallStateListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            telephonyCallbackHandler.startListening()
        } else {
            phoneStateListenerHandler.startListening()
        }
    }

    fun stopCallStateListener(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            telephonyCallbackHandler.stopListening()
        } else {
            phoneStateListenerHandler.stopListening()
        }
    }

}

interface CommonCallStateFinder{
    fun onCallStateChanged(state: Int)
}