package com.softwareallin1.aioscrm.ui.leads.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.LeadsResponse
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
import com.softwareallin1.aioscrm.ui.leads.repository.LeadsRepository
import com.softwareallin1.aioscrm.utils.Validation
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey.STAFF_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLeadViewModel @Inject constructor(
    private val repository: LeadsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {
    lateinit var staffListResponse: MutableLiveData<ResponseHandler<ResponseData<StaffListResponse>?>>

    val status: MutableLiveData<String> = MutableLiveData()
    val source: MutableLiveData<String> = MutableLiveData()
    val staff: MutableLiveData<String> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val position: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val website: MutableLiveData<String> = MutableLiveData()
    val phoneNumber: MutableLiveData<String> = MutableLiveData()
    val leadValue: MutableLiveData<String> = MutableLiveData()
    val companyName: MutableLiveData<String> = MutableLiveData()
    val address: MutableLiveData<String> = MutableLiveData()
    val city: MutableLiveData<String> = MutableLiveData()
    val state: MutableLiveData<String> = MutableLiveData()
    val country: MutableLiveData<String> = MutableLiveData()
    val zipCode: MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    var isPublic = MutableLiveData<Boolean>()
    val isContractToday = MutableLiveData<Boolean>()

    fun initVariables() {
        staffListResponse = MutableLiveData<ResponseHandler<ResponseData<StaffListResponse>?>>()
    }

    fun leadValidation() {
        hideKeyboard()
        when {
            !Validation.isNotNull(status.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_status)
            }
            !Validation.isNotNull(staff.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_staff)
            }
            !Validation.isNotNull(source.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_source)
            }
            !Validation.isNotNull(name.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_name)
            }
            !Validation.isNotNull(position.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_pos)
            }
            !Validation.isNotNull(email.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_email)
            }
            !Validation.isNotNull(website.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_web)
            }
            !Validation.isNotNull(phoneNumber.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_number)
            }
            !Validation.isNotNull(leadValue.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_leadvalue)
            }
            !Validation.isNotNull(companyName.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_companyName)
            }
            !Validation.isNotNull(address.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_address)
            }
            !Validation.isNotNull(city.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_city)
            }
            !Validation.isNotNull(state.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_state)
            }
            !Validation.isNotNull(country.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_country)
            }
            !Validation.isNotNull(zipCode.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_zipcode)
            }
            !Validation.isNotNull(desc.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_desc)
            }
            else -> {
                leadSaved()
            }
        }
    }

    fun getLeads() {
        viewModelScope.launch {
            staffListResponse.postValue(ResponseHandler.Loading)
            staffListResponse.value = repository.getStaffList()
        }
    }

    private fun leadSaved() {
        showSnackbarMessage(R.string.error_enter_save)
    }

}