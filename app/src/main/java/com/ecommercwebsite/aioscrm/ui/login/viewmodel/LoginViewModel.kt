package com.ecommercwebsite.aioscrm.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.utils.MyPreference
import com.ecommercwebsite.aioscrm.utils.SingleLiveEvent
import com.ecommercwebsite.aioscrm.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val myPreference: MyPreference
) : ViewModelBase() {
    val email: MutableLiveData<String> = MutableLiveData("Kishan@gmail.com")
    val password: MutableLiveData<String> = MutableLiveData("12345678")
    lateinit var onLogin: SingleLiveEvent<Boolean>

    fun initVariables() {
        onLogin = SingleLiveEvent()
    }

    fun loginClick() {
        hideKeyboard()
        when {
            !Validation.isNotNull(
                email.value.toString().trim()
            ) && !Validation.isNotNull(
                password.value.toString().trim()
            )
            -> {
                showSnackbarMessage(R.string.error_enter_email_password)
            }

            !Validation.isNotNull(email.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_email)
            }

            !Validation.isNotNull(password.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_password)
            }

            else -> {
                onLogin.call()
            }
        }
    }

}