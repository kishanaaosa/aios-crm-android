package com.ecommercwebsite.aioscrm.ui.attendance.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import com.ecommercwebsite.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FillAttendanceViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var onFillAttendanceClick: SingleLiveEvent<Boolean>
    lateinit var onTakePhotoClick: SingleLiveEvent<Boolean>
    lateinit var lat: MutableLiveData<String>
    lateinit var long: MutableLiveData<String>
    lateinit var userProfile: MutableLiveData<String>
    var imageFile: File? = null


    fun initVariables() {
        onFillAttendanceClick = SingleLiveEvent()
        onTakePhotoClick = SingleLiveEvent()
        lat = MutableLiveData<String>()
        long = MutableLiveData<String>()
        userProfile = MutableLiveData()
    }

    fun onTakePhotoClick() {
        onTakePhotoClick.call()
    }

    fun onFillAttendanceClick() {
        onFillAttendanceClick.call()
    }

}