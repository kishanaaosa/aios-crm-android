package com.softwareallin1.aioscrm.ui.appointly.viewmodel

import androidx.lifecycle.MutableLiveData
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.ui.appointly.repository.AppointmentRepository
import com.softwareallin1.aioscrm.utils.Validation
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddAppointmentViewModel @Inject constructor(
    private val  repository: AppointmentRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    val subject : MutableLiveData<String> = MutableLiveData()
    val desc : MutableLiveData<String> = MutableLiveData()
    val related : MutableLiveData<String> = MutableLiveData()
    val dateTime : MutableLiveData<String> = MutableLiveData()
    val type : MutableLiveData<String> = MutableLiveData()
    val repeat : MutableLiveData<String> = MutableLiveData()
    val location : MutableLiveData<String> = MutableLiveData()
    val appointment : MutableLiveData<String> = MutableLiveData()
    val attendance : MutableLiveData<String> = MutableLiveData()
    val sms : MutableLiveData<Boolean> = MutableLiveData()
    val email : MutableLiveData<Boolean> = MutableLiveData()

    fun validationAppointmentForm(){

        when{

            !Validation.isNotNull(subject.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_sub)
            }

            !Validation.isNotNull(desc.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_desc)
            }

            !Validation.isNotNull(related.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_related)
            }

            !Validation.isNotNull(dateTime.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_dateTime)
            }

            !Validation.isNotNull(attendance.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_attendance)
            }


            !Validation.isNotNull(appointment.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_appointment)
            }

            !Validation.isNotNull(type.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_attendance_type)
            }

            !Validation.isNotNull(repeat.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_repeat)
            }

            else -> { saveAppointment() }

        }

    }


    fun saveAppointment(){

        showSnackbarMessage(R.string.add_appointment)

    }



}