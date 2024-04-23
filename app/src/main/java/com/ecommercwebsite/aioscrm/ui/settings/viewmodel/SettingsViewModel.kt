package com.ecommercwebsite.aioscrm.ui.settings.viewmodel

import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel@Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {
}