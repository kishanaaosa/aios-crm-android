package com.softwareallin1.aioscrm.ui.tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.ui.tasks.repository.TaskRepository
import com.softwareallin1.aioscrm.utils.Validation
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val  repository: TaskRepository,
    private val myPreference: MyPreference
) : ViewModelBase() {

    val priority : MutableLiveData<String> = MutableLiveData()
    val subject : MutableLiveData<String> = MutableLiveData()
    val hourlyRate : MutableLiveData<String> = MutableLiveData()
    val startDate : MutableLiveData<String> = MutableLiveData()
    val dueDate : MutableLiveData<String> = MutableLiveData()
    val repeat : MutableLiveData<String> = MutableLiveData()
    val related : MutableLiveData<String> = MutableLiveData()
    val search : MutableLiveData<String> = MutableLiveData()
    val total : MutableLiveData<String> = MutableLiveData()
    val desc : MutableLiveData<String> = MutableLiveData()
    val billable : MutableLiveData<Boolean> = MutableLiveData()

    fun taskValidation(){

        hideKeyboard()

        when {

            !Validation.isNotNull(priority.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_priority)
            }

            !Validation.isNotNull(subject.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_sub)
            }

            !Validation.isNotNull(hourlyRate.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_hourlyRate)
            }

            !Validation.isNotNull(startDate.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_startDate)
            }

            !Validation.isNotNull(dueDate.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_dueDate)
            }

            !Validation.isNotNull(related.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_related)
            }

            !Validation.isNotNull(repeat.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_repeat)
            }

            !Validation.isNotNull(search.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_search)
            }

            !Validation.isNotNull(total.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_total)
            }

            !Validation.isNotNull(desc.value.toString().trim()) -> {
                showSnackbarMessage(R.string.error_enter_desc)
            }


            else -> { leadSave() }

        }

    }

    private fun leadSave() {

        showSnackbarMessage(R.string.error_enter_save)

    }




}