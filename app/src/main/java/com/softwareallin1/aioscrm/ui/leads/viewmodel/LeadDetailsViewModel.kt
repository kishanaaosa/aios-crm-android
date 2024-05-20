package com.softwareallin1.aioscrm.ui.leads.viewmodel

import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.ui.leads.repository.ProfileDetailRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LeadDetailsViewModel @Inject constructor(
    private val repository: ProfileDetailRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {




}