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
class AddLeadViewModel @Inject constructor(
    private val repository: LeadsRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {
    val status : MutableLiveData<String> = MutableLiveData()
    val source : MutableLiveData<String> = MutableLiveData()
    val staff : MutableLiveData<String> = MutableLiveData()
    val name : MutableLiveData<String> = MutableLiveData()
    val position : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val website : MutableLiveData<String> = MutableLiveData()
    val phoneNumber : MutableLiveData<String> = MutableLiveData()
    val leadValue : MutableLiveData<String> = MutableLiveData()
    val companyName : MutableLiveData<String> = MutableLiveData()
    val address : MutableLiveData<String> = MutableLiveData()
    val city : MutableLiveData<String> = MutableLiveData()
    val state : MutableLiveData<String> = MutableLiveData()
    val country : MutableLiveData<String> = MutableLiveData()
    val zipCode : MutableLiveData<String> = MutableLiveData()
    val desc : MutableLiveData<String> = MutableLiveData()
    var isPublic = MutableLiveData<Boolean>()
    val isContractToday = MutableLiveData<Boolean>()

    fun initVariables() {

    }


}