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
                if (androidx.core.app.ActivityCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.READ_PHONE_STATE
                    ) != android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
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