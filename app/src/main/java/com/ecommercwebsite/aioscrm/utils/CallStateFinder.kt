package com.ecommercwebsite.aioscrm.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.TelephonyCallback
import android.telephony.TelephonyCallback.CallStateListener
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi


class CallStateFinder : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        registerCustomTelephonyCallback(context)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    internal class CustomTelephonyCallback(callBack: CallBack) : TelephonyCallback(),
        CallStateListener {
        private val mCallBack: CallBack

        init {
            mCallBack = callBack
        }

        override fun onCallStateChanged(state: Int) {
            mCallBack.callStateChanged(state)
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun registerCustomTelephonyCallback(context: Context?) {
        val telephony = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        telephony.registerTelephonyCallback(
            context.mainExecutor,
            CustomTelephonyCallback(object : CallBack {
                override fun callStateChanged(state: Int) {
                    val myState = state
                }
            })
        )
    }


}

internal interface CallBack {
    fun callStateChanged(state: Int)
}