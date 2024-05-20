package com.softwareallin1.aioscrm.ui.myaccount.viewmodel

import androidx.lifecycle.MutableLiveData
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {

    var userName: MutableLiveData<String> = MutableLiveData("")
    var userEmail: MutableLiveData<String> = MutableLiveData("")
    var userProfile: MutableLiveData<String> = MutableLiveData("")

    fun initVariables() {
        getProfileData()
    }

    private fun getProfileData() {
        userName.value = myPreference.getValueString(PrefKey.USER_NAME,"")
        userEmail.value = myPreference.getValueString(PrefKey.EMAIL,"")
        userProfile.value = myPreference.getValueString(PrefKey.PROFILE_IMAGE,"")
    }

}