package com.ecommercwebsite.aioscrm.ui.home.viewmodel

import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import com.ecommercwebsite.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {

    lateinit var onStartCalling: SingleLiveEvent<Boolean>


    fun initVariables() {
        onStartCalling = SingleLiveEvent()

    }

    fun onStartCalling() {
        onStartCalling.call()
    }

}