package com.softwareallin1.aioscrm.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.home.model.CheckAttendanceResponse
import com.softwareallin1.aioscrm.ui.home.repository.HomeRepository
import com.softwareallin1.aioscrm.utils.SingleLiveEvent
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import com.example.android_practical.ui.home.CallerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myPreference: MyPreference,
    private val repository: HomeRepository
) : ViewModelBase() {

    lateinit var checkAttendanceResponse: MutableLiveData<ResponseHandler<ResponseData<CheckAttendanceResponse>?>>


    lateinit var onStartCalling: SingleLiveEvent<Boolean>
    var logsList: ArrayList<CallerModel?>? = null


  /*  var callLogList = MutableLiveData<List<CallLogItem>>()
    var missCallList = MutableLiveData<List<CallLogItem>>()
    var dialCallList = MutableLiveData<List<CallLogItem>>()
    var inComingCallList = MutableLiveData<List<CallLogItem>>()
    var outGoingCallList = MutableLiveData<List<CallLogItem>>()*/

   /* fun getCallLogs(contentResolver: ContentResolver){

        val calllist = mutableListOf<CallLogItem>()
        val misslist = mutableListOf<CallLogItem>()
        val incominglist = mutableListOf<CallLogItem>()
        val outgoinglist = mutableListOf<CallLogItem>()
        val diallist = mutableListOf<CallLogItem>()

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedTodayDate = formatter.format(Calendar.getInstance().timeInMillis)
        val today = formattedTodayDate.toString()


        CoroutineScope(Dispatchers.IO).launch{


                val cursor: Cursor? = contentResolver.query(
                    CallLog.Calls.CONTENT_URI,
                    null,
                    null,
                    null,
                    "${CallLog.Calls.DATE} DESC"
                )

                cursor?.use {
                    val numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER)
                    val dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE)
                    val typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE)
                    val durationIndex = cursor.getColumnIndex(CallLog.Calls.DURATION)

                    while (cursor.moveToNext()) {
                        val number = cursor.getString(numberIndex)
                        val date = cursor.getLong(dateIndex)
                        val type = cursor.getInt(typeIndex)
                        val duration = cursor.getString(durationIndex)
                        val formattedTodayDate = formatter.format(date)
                        val callDate = formattedTodayDate.toString()

                        val callType = when (type) {
                            CallLog.Calls.INCOMING_TYPE -> "Incoming"
                            CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                            CallLog.Calls.MISSED_TYPE -> "Missed"
                            CallLog.Calls.REJECTED_TYPE -> "dialer"
                            else -> "Unknown"
                        }

                        val item = CallLogItem(number, date, callType, duration)

                        if (today.equals(callDate)) {

                            when (type) {

                                CallLog.Calls.INCOMING_TYPE -> {
                                    incominglist.add(item)
                                }

                                CallLog.Calls.OUTGOING_TYPE -> {

                                    if (duration.toInt() > 0) {
                                        outgoinglist.add(item)
                                    } else {
                                        diallist.add(item)
                                    }
                                }

                                CallLog.Calls.MISSED_TYPE -> {
                                    misslist.add(item)
                                }

                            }

                            calllist.add(item)

                        }

//                        Log.i("TAG", "getCallLogs: $number $today $callDate $type $duration" )

                    }

                    inComingCallList.postValue(incominglist)
                    outGoingCallList.postValue(outgoinglist)
                    missCallList.postValue(misslist)
                    dialCallList.postValue(diallist)


            }

        }

    }*/


    fun initVariables() {
        logsList = arrayListOf()
        onStartCalling = SingleLiveEvent()
        checkAttendanceResponse = MutableLiveData<ResponseHandler<ResponseData<CheckAttendanceResponse>?>>()
    }

    fun onStartCalling() {
        onStartCalling.call()
    }

    fun checkAttendance() {
        viewModelScope.launch {
            checkAttendanceResponse.postValue(ResponseHandler.Loading)
            checkAttendanceResponse.value = repository.checkAttendance(myPreference.getValueString(PrefKey.STAFF_ID,"0").toString())
        }
    }

}