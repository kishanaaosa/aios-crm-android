package com.ecommercwebsite.aioscrm.ui.calls.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.calls.model.CallLogListResponse
import com.ecommercwebsite.aioscrm.ui.calls.repository.CallsRepository
import com.ecommercwebsite.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallsViewModel @Inject constructor(
    private val repository: CallsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var callLigListResponse: MutableLiveData<ResponseHandler<ResponseData<CallLogListResponse>?>>


    fun initVariables() {
        callLigListResponse = MutableLiveData<ResponseHandler<ResponseData<CallLogListResponse>?>>()
    }

    fun getLeadsWisePhoneCallLog() {
        viewModelScope.launch {
            callLigListResponse.postValue(ResponseHandler.Loading)
            callLigListResponse.value = repository.getLeads("274")
        }
    }

}