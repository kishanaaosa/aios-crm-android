package com.softwareallin1.aioscrm.ui.appointly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.appointly.model.AppointmentResponse
import com.softwareallin1.aioscrm.ui.appointly.repository.AppointmentRepository
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointlyViewModel @Inject constructor(
    private val repository: AppointmentRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {
    lateinit var totalRecords: MutableLiveData<String>
    lateinit var totalFilters: MutableLiveData<String>
    lateinit var appointmentResponse: MutableLiveData<ResponseHandler<ResponseData<AppointmentResponse>?>>

    fun initVariables() {
        totalRecords = MutableLiveData("0")
        totalFilters = MutableLiveData("0")
        appointmentResponse = MutableLiveData<ResponseHandler<ResponseData<AppointmentResponse>?>>()
    }

    fun getAppointments() {
        viewModelScope.launch {
            appointmentResponse.postValue(ResponseHandler.Loading)
            appointmentResponse.value = repository.getAppointments()
        }
    }

}