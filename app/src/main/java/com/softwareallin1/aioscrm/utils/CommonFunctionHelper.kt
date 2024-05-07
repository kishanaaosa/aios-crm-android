package com.softwareallin1.aioscrm.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import com.softwareallin1.aioscrm.R


object CommonFunctionHelper {

    fun getLeadStatusId(value: Int): String {
        return when (value) {
            R.id.rbNotReachable -> "1"
            R.id.rbNotInterested -> "2"
            R.id.rbNew -> "3"
            R.id.rbNotReceived -> "4"
            R.id.rbInterested -> "5"
            R.id.rbBusyNow -> "6"
            R.id.rbFuturePurchase -> "7"
            R.id.rbOtherService -> "8"
            R.id.rbMayBeInterested -> "9"
            else -> "0"
        }
    }

    fun getUniqueId(): String {
        return (Build.BOARD.length % 10).toString() +
                (Build.BRAND.length % 10).toString() +
                (Build.DEVICE.length % 10).toString() +
                (Build.DISPLAY.length % 10).toString() +
                (Build.HOST.length % 10).toString() +
                (Build.ID.length % 10).toString() +
                (Build.MANUFACTURER.length % 10).toString() +
                (Build.MODEL.length % 10).toString() +
                (Build.PRODUCT.length % 10).toString() +
                (Build.TAGS.length % 10).toString() +
                (Build.TYPE.length % 10).toString() +
                (Build.USER.length % 10).toString()
    }

    fun setFadeAnimation(view: View) {
        //val anim = AlphaAnimation(0.0f, 1.0f)
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        anim.duration = 300
        view.startAnimation(anim)
    }

    fun formatSecondsToMinutes(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%d:%02d", minutes, remainingSeconds)
    }

    fun openWhatsAppChat(context: Context, toNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse("https://wa.me/$toNumber")

        intent.data = uri
        intent.setPackage("com.whatsapp")

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "WhatsApp Not Installed", Toast.LENGTH_SHORT).show()
        }
    }


    /* fun openWhatsAppChat(context: Context, phoneNumber: String) {
         val packageManager = context.packageManager

         // Check if WhatsApp is installed
         val isWhatsAppInstalled = isAppInstalled(packageManager, "com.whatsapp")
         if (!isWhatsAppInstalled) {
             // WhatsApp is not installed, handle this case
             Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
             return
         }

         try {
             // Create intent to open WhatsApp with a specific phone number
             val intent = Intent(Intent.ACTION_VIEW)
             intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
             context.startActivity(intent)
         } catch (e: Exception) {
             Toast.makeText(context, "Failed to open WhatsApp", Toast.LENGTH_SHORT).show()
             e.printStackTrace()
         }
     }*/

    private fun isAppInstalled(packageManager: PackageManager, packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}