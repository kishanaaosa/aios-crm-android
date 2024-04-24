package com.ecommercwebsite.aioscrm.ui.settings.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.MyPreference
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {
    var language = MutableLiveData<String>("en")
    var pushNotificationSaate = MutableLiveData<Boolean?>(false)
    var smsState = MutableLiveData<Boolean?>(false)
    var emailState = MutableLiveData<Boolean?>(false)
    var onSave = SingleLiveEvent<Boolean>()
    var onEnglish = SingleLiveEvent<Boolean>()
    var onHindi = SingleLiveEvent<Boolean>()


    fun initVariables() {
        language.value = "en"
        pushNotificationSaate.value = true
        pushNotificationSaate.value = true
    }

    fun pushNotificationCheckedChange(isChecked: Boolean) {
        pushNotificationSaate.value = isChecked
    }

    fun smsStateCheckedChange(isChecked: Boolean) {
        smsState.value = isChecked
    }

    fun emailStateCheckedChange(isChecked: Boolean) {
        emailState.value = isChecked
    }

    fun englishClick() {
        language.value = "en"
        onEnglish.value = true
    }

    fun hindiClick() {
        language.value = "hi"
        onHindi.value = true
    }

    fun saveClick() {
        onSave.value = true
    }

}