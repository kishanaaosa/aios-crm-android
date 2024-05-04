package com.softwareallin1.aioscrm.ui.attendance.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.attendance.model.FillAttendanceResponse
import com.softwareallin1.aioscrm.ui.attendance.repository.AttendanceRepository
import com.softwareallin1.aioscrm.utils.SingleLiveEvent
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FillAttendanceViewModel @Inject constructor(
    private val myPreference: MyPreference,
    private val repository: AttendanceRepository
) : ViewModelBase() {

    lateinit var onFillAttendanceClick: SingleLiveEvent<Boolean>
    lateinit var onTakePhotoClick: SingleLiveEvent<Boolean>
    lateinit var lat: MutableLiveData<String>
    lateinit var long: MutableLiveData<String>
    lateinit var userProfile: MutableLiveData<String>
    var imageFile: File? = null
    lateinit var fillAttendanceResponse: MutableLiveData<ResponseHandler<ResponseData<FillAttendanceResponse>?>>


    fun initVariables() {
        onFillAttendanceClick = SingleLiveEvent()
        onTakePhotoClick = SingleLiveEvent()
        lat = MutableLiveData<String>()
        long = MutableLiveData<String>()
        userProfile = MutableLiveData()
        fillAttendanceResponse =
            MutableLiveData<ResponseHandler<ResponseData<FillAttendanceResponse>?>>()
    }

    fun onTakePhotoClick() {
        onTakePhotoClick.call()
    }

    fun onFillAttendanceClick() {
        onFillAttendanceClick.call()
    }

    fun fillAttendance() {
        viewModelScope.launch {
            fillAttendanceResponse.value = ResponseHandler.Loading
            val responseFromApi =
                repository.fillAttendance(
                    myPreference.getValueString(PrefKey.STAFF_ID, "0").toString(),
                    lat.value.toString(),
                    long.value.toString(),
                    imageFile?.asRequestBody(
                        "image/*".toMediaType()
                    )?.let {
                        MultipartBody.Part.createFormData(
                            "image",
                            imageFile?.name,
                            it
                        )
                    }
                )
            fillAttendanceResponse.value = responseFromApi!!
        }
    }

}