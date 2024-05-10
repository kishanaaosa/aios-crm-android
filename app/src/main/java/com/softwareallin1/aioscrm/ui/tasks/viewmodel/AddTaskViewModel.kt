package com.softwareallin1.aioscrm.ui.tasks.viewmodel

import android.content.SharedPreferences
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.ui.tasks.repository.AddTaskRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val  repository: AddTaskRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {



}