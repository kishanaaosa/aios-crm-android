package com.softwareallin1.aioscrm.ui.tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var totalRecords: MutableLiveData<String>
    lateinit var totalFilters: MutableLiveData<String>

    fun initVariables() {
        totalRecords = MutableLiveData("0")
        totalFilters = MutableLiveData("0")
    }

}