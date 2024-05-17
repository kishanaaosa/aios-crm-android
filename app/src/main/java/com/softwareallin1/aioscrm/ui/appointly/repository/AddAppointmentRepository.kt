package com.softwareallin1.aioscrm.ui.appointly.repository

import com.softwareallin1.aioscrm.base.BaseRepository
import com.softwareallin1.aioscrm.network.ApiInterface
import javax.inject.Inject


class AddAppointmentRepository  @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

}