package com.ecommercwebsite.aioscrm.ui.autocall.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.ecommercwebsite.aioscrm.ui.autocall.repository.AutoCallRepository
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import com.ecommercwebsite.aioscrm.utils.sharedpref.MyPreference
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoCallViewModel @Inject constructor(
    private val repository: AutoCallRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {
    var timer: MutableLiveData<String> = MutableLiveData<String>("0")
     var onCancel = SingleLiveEvent<Boolean>()
    lateinit var leadListResponse: MutableLiveData<ResponseHandler<ResponseData<AutoCallLeadsResponse>?>>
    var leadsList: AutoCallLeadsResponse = AutoCallLeadsResponse(arrayListOf())

    fun initVariables() {
        onCancel = SingleLiveEvent()
        leadListResponse = MutableLiveData<ResponseHandler<ResponseData<AutoCallLeadsResponse>?>>()
    }

    fun onCancel(){
        onCancel.value = true
    }

    fun getAutoCallLeads() {
        viewModelScope.launch {
            leadListResponse.postValue(ResponseHandler.Loading)
            leadListResponse.value = repository.getAutoCallLeads(myPreference.getValueString(PrefKey.STAFF_ID,"0").toString())
        }
    }

}