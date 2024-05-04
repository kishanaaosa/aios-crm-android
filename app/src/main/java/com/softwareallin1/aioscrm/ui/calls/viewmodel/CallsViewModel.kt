package com.softwareallin1.aioscrm.ui.calls.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.calls.model.CallLogListResponse
import com.softwareallin1.aioscrm.ui.calls.repository.CallsRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.example.android_practical.ui.home.CallerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallsViewModel @Inject constructor(
    private val repository: CallsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    var logsList: ArrayList<CallerModel?>? = null
    lateinit var totalRecords: MutableLiveData<String>
    lateinit var totalFilters: MutableLiveData<String>

    lateinit var callLigListResponse: MutableLiveData<ResponseHandler<ResponseData<CallLogListResponse>?>>


    fun initVariables() {
        totalRecords = MutableLiveData("0")
        totalFilters = MutableLiveData("0")
        logsList = arrayListOf()
        callLigListResponse = MutableLiveData<ResponseHandler<ResponseData<CallLogListResponse>?>>()
    }

    fun getLeadsWisePhoneCallLog() {
        viewModelScope.launch {
            callLigListResponse.postValue(ResponseHandler.Loading)
            callLigListResponse.value = repository.getLeads("274")
        }
    }

}