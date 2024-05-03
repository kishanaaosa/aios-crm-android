package com.example.android_practical.ui.home

import android.graphics.Bitmap

data class CallerModel(
    var name: String?,
    var number: String,
    var date: String,
    var time: String,
    val callType: Int,
    var photo: Bitmap?,
    var duration: String?
){
}
