package com.softwareallin1.aioscrm.ui.leads.viewmodel

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.ui.leads.repository.LeadsRepository
import com.softwareallin1.aioscrm.ui.leads.repository.ProfileDetailRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val repository: ProfileDetailRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {




}