package com.ecommercwebsite.aioscrm.ui.home

import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {


    fun initVariables() {

    }

}