package com.ecommercwebsite.aioscrm.utils

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

class PhoneStateListenerHandler(private val telephonyManager: TelephonyManager, private val listener: PhoneStateListenerListener) {
    private val phoneStateListener = object : PhoneStateListener() {
        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)
            listener.onCallStateChanged(state)
        }
    }

    fun startListening() {
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    fun stopListening() {
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
    }
}

interface PhoneStateListenerListener {
    fun onCallStateChanged(state: Int)
}