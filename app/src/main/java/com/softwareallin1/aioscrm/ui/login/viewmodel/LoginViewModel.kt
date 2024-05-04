package com.softwareallin1.aioscrm.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.login.model.LoginResponse
import com.softwareallin1.aioscrm.ui.login.repository.LoginRepository
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.SingleLiveEvent
import com.softwareallin1.aioscrm.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    lateinit var onLogin: SingleLiveEvent<Boolean>
    lateinit var loginResponse: MutableLiveData<ResponseHandler<ResponseData<LoginResponse>?>>

    fun initVariables() {
        onLogin = SingleLiveEvent()
        loginResponse = MutableLiveData<ResponseHandler<ResponseData<LoginResponse>?>>()
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
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginResponse.postValue(ResponseHandler.Loading)
            loginResponse.value =
                repository.login(
                    email.value.toString().trim(),
                    password.value.toString().trim()
                )
        }
    }

}