package com.softwareallin1.aioscrm.ui.leads.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import com.softwareallin1.aioscrm.ui.leads.repository.LeadsRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey.STAFF_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeadsViewModel @Inject constructor(
    private val repository: LeadsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var totalRecords: MutableLiveData<String>
    lateinit var totalFilters: MutableLiveData<String>

    lateinit var leadsResponse: MutableLiveData<ResponseHandler<ResponseData<LeadsResponse>?>>

    fun initVariables() {
        totalRecords = MutableLiveData("0")
        totalFilters = MutableLiveData("0")
        leadsResponse = MutableLiveData<ResponseHandler<ResponseData<LeadsResponse>?>>()
    }

     fun getLeads() {
        viewModelScope.launch {
            leadsResponse.postValue(ResponseHandler.Loading)
            leadsResponse.value = repository.getLeads(myPreference.getValueString(STAFF_ID,"0").toString())
        }
    }

}