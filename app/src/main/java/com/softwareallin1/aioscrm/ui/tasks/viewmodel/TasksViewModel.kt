package com.softwareallin1.aioscrm.ui.tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.leads.model.StaffListResponse
import com.softwareallin1.aioscrm.ui.tasks.model.TaskListResponse
import com.softwareallin1.aioscrm.ui.tasks.repository.TaskRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var totalRecords: MutableLiveData<String>
    lateinit var totalFilters: MutableLiveData<String>
    lateinit var taskListResponse: MutableLiveData<ResponseHandler<ResponseData<TaskListResponse>?>>


    fun initVariables() {
        totalRecords = MutableLiveData("0")
        totalFilters = MutableLiveData("0")
        taskListResponse = MutableLiveData<ResponseHandler<ResponseData<TaskListResponse>?>>()

    }

    fun getTasks() {
        viewModelScope.launch {
            taskListResponse.postValue(ResponseHandler.Loading)
            taskListResponse.value = repository.getStaffList()
        }
    }

}