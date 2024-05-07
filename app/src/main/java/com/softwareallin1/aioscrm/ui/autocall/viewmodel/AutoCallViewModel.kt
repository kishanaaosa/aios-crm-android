package com.softwareallin1.aioscrm.ui.autocall.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.autocall.model.AddNoteOnLeadResponse
import com.softwareallin1.aioscrm.ui.autocall.model.AutoCallLeadsResponse
import com.softwareallin1.aioscrm.ui.autocall.model.ChangeLeadStatusResponse
import com.softwareallin1.aioscrm.ui.autocall.model.LeadsList
import com.softwareallin1.aioscrm.ui.autocall.repository.AutoCallRepository
import com.softwareallin1.aioscrm.utils.SingleLiveEvent
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoCallViewModel @Inject constructor(
    private val repository: AutoCallRepository,
    val myPreference: MyPreference
) : ViewModelBase() {
    var timer: MutableLiveData<String> = MutableLiveData<String>("0")
    var onCancel = SingleLiveEvent<Boolean>()
    var onNext = SingleLiveEvent<Boolean>()
    lateinit var leadListResponse: MutableLiveData<ResponseHandler<ResponseData<AutoCallLeadsResponse>?>>
    var leadsList: AutoCallLeadsResponse = AutoCallLeadsResponse(arrayListOf())
    var randomList: MutableList<Int> = mutableListOf()
    var isCallStarted: MutableLiveData<Boolean> = MutableLiveData(false)
    var isCallFinished: MutableLiveData<Boolean> = MutableLiveData(false)
    var selectedLead: MutableLiveData<LeadsList?> = MutableLiveData()
    lateinit var changeLeadStatusResponse: MutableLiveData<ResponseHandler<ResponseData<ChangeLeadStatusResponse>?>>
    var leadStatus: MutableLiveData<String> = MutableLiveData("0")
    var isLeadStatusChanged: MutableLiveData<Boolean> = MutableLiveData(false)
    var note: MutableLiveData<String> = MutableLiveData("")
    lateinit var addNoteOnLeadResponse: MutableLiveData<ResponseHandler<ResponseData<AddNoteOnLeadResponse>?>>
    var isNotAdded: MutableLiveData<Boolean> = MutableLiveData(false)


    fun initVariables() {
        onCancel = SingleLiveEvent()
        onNext = SingleLiveEvent()
        leadListResponse = MutableLiveData<ResponseHandler<ResponseData<AutoCallLeadsResponse>?>>()
        changeLeadStatusResponse =
            MutableLiveData<ResponseHandler<ResponseData<ChangeLeadStatusResponse>?>>()
        addNoteOnLeadResponse =
            MutableLiveData<ResponseHandler<ResponseData<AddNoteOnLeadResponse>?>>()
    }

    fun generateRandomList() {
        randomList = (0..(leadsList.leads?.size?.minus(1) ?: 0)).shuffled().toMutableList()
    }

    fun nextRandom(): Int? {
        var random: Int? = null
        if (randomList.isNotEmpty()) {
            random = randomList.random()
            randomList.remove(random)
        } else {
            random = null
        }
        return random
    }

    fun getAutoCallLeads() {
        viewModelScope.launch {
            leadListResponse.postValue(ResponseHandler.Loading)
            leadListResponse.value = repository.getAutoCallLeads(
                myPreference.getValueString(PrefKey.STAFF_ID, "0").toString()
            )
        }
    }

    fun changeLeadStatus() {
        viewModelScope.launch {
            changeLeadStatusResponse.postValue(ResponseHandler.Loading)
            changeLeadStatusResponse.value = repository.changeLeadStatus(
                selectedLead.value?.id,
                leadStatus.value
            )
        }
    }

    fun addNotesOnLead() {
        viewModelScope.launch {
            addNoteOnLeadResponse.postValue(ResponseHandler.Loading)
            addNoteOnLeadResponse.value = repository.addNotesOnLead(
                selectedLead.value?.id,
                note.value.toString().trim()
            )
        }
    }

}