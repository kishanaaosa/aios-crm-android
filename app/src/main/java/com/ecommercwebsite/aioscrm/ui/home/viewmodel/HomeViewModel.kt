package com.ecommercwebsite.aioscrm.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.home.model.CheckAttendanceResponse
import com.ecommercwebsite.aioscrm.ui.home.repository.HomeRepository
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import com.ecommercwebsite.aioscrm.utils.sharedpref.MyPreference
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myPreference: MyPreference,
    private val repository: HomeRepository
) : ViewModelBase() {

    lateinit var checkAttendanceResponse: MutableLiveData<ResponseHandler<ResponseData<CheckAttendanceResponse>?>>


    lateinit var onStartCalling: SingleLiveEvent<Boolean>


    fun initVariables() {
        onStartCalling = SingleLiveEvent()
        checkAttendanceResponse = MutableLiveData<ResponseHandler<ResponseData<CheckAttendanceResponse>?>>()
    }

    fun onStartCalling() {
        onStartCalling.call()
    }

    fun checkAttendance() {
        viewModelScope.launch {
            checkAttendanceResponse.postValue(ResponseHandler.Loading)
            checkAttendanceResponse.value = repository.checkAttendance(myPreference.getValueString(PrefKey.STAFF_ID,"0").toString())
        }
    }

}