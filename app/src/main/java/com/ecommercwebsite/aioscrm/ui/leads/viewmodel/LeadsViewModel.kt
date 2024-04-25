package com.ecommercwebsite.aioscrm.ui.leads.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.leads.repository.LeadsRepository
import com.ecommercwebsite.aioscrm.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeadsViewModel @Inject constructor(
    private val repository: LeadsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var leadsResponse: MutableLiveData<ResponseHandler<ResponseData<LeadsResponse>?>>

    fun initVariables() {
        leadsResponse = MutableLiveData<ResponseHandler<ResponseData<LeadsResponse>?>>()
    }

     fun getLeads() {
        viewModelScope.launch {
            leadsResponse.postValue(ResponseHandler.Loading)
            leadsResponse.value = repository.getLeads("11")
        }
    }

}