package com.softwareallin1.aioscrm.ui.home.ui

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.CallLog
import android.util.Log
import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentHomeBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.home.model.CheckAttendanceResponse
import com.softwareallin1.aioscrm.ui.home.viewmodel.HomeViewModel
import com.softwareallin1.aioscrm.utils.DebugLog
import com.softwareallin1.aioscrm.utils.permissions.Permission
import com.softwareallin1.aioscrm.utils.permissions.PermissionManager
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import com.example.android_practical.ui.home.CallerModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class HomeFragment : FragmentBase<HomeViewModel, FragmentHomeBinding>() {
    private val permissionManager = PermissionManager.from(this)
    var missed = 0
    var incoming = 0
    var outgoing = 0
    var unAnswered = 0
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Home",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        setUpObserver()
        checkPermissions()
        viewModel.checkAttendance()
    }

    private fun setUpObserver() {
        viewModel.onStartCalling.observe(this) {
            mPref.setValueBoolean(PrefKey.IS_AUTO_CALLING, true)
            (activity as MainActivity).navigateToNextScreenThroughDirections(HomeFragmentDirections.actionHomeFragmentToAutoCallFragment())
        }
        viewModel.checkAttendanceResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<CheckAttendanceResponse>?> -> {
                    viewModel.showProgressBar(false)
                    if (it.response?.data?.isAttendance == false) {
                        (activity as MainActivity).navigateToNextScreenThroughDirections(
                            HomeFragmentDirections.actionHomeFragmentToFillAttendanceFragment()
                        )
                    }
                }
            }
        })
        /* viewModel.missCallList.observe(viewLifecycleOwner) {
             getDataBinding().tvMissedCallsCount.text = it.size.toString()
         }
         viewModel.dialCallList.observe(viewLifecycleOwner) {
             getDataBinding().tvDialCallsCount.text = it.size.toString()
         }
         viewModel.inComingCallList.observe(viewLifecycleOwner) {
             getDataBinding().tvInComingCallCount.text = it.size.toString()
         }
         viewModel.outGoingCallList.observe(viewLifecycleOwner) {
             getDataBinding().tvUnAnsweredCallsCount.text = it.size.toString()
         }*/
    }

    private fun checkPermissions() {
        permissionManager
            .request(
                Permission.PostNotification
            )
            .rationale(getString(R.string.post_notification))
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    checkPermissionforLog()
                    // viewModel.showSnackbarMessage("Permission Granted")
                } else {
                    // viewModel.showSnackbarMessage("Permission Denied")
                }
            }
    }

    private fun checkPermissionforLog() {
        permissionManager
            .request(
                Permission.ReadWriteLog,
                Permission.ReadPhoneState,
                Permission.CallPhone
            )
            .rationale(getString(R.string.post_notification))
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {

                   // getCallLogList()
                    // viewModel.showSnackbarMessage("Permission Granted")
                } else {
                    // viewModel.showSnackbarMessage("Permission Denied")
                }
            }
    }

    private fun getCallLogList() {
         missed = 0
         incoming = 0
         outgoing = 0
         unAnswered = 0
            //viewModel.showProgressBar(true)
            Log.i("readContacts", "Reading Contacts")
            val contentResolver = context?.contentResolver
            val callLogsUri: Uri = Uri.parse("content://call_log/calls")
            val callLogsCursor: Cursor? =
                contentResolver?.query(
                    callLogsUri,
                    null,
                    null,
                    null,
                    android.provider.CallLog.Calls.DEFAULT_SORT_ORDER
                )
            if (callLogsCursor!!.moveToFirst()) {
                do {
                    val name: String? =
                        callLogsCursor.getString(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME))
                    val photoUri: String? =
                        callLogsCursor.getString(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_PHOTO_URI))
                    var photo: Bitmap? = null
                    val number: String =
                        callLogsCursor.getString(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER))
                    val date: Long =
                        callLogsCursor.getLong(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.DATE))
                    val callType: Int =
                        Integer.parseInt(
                            callLogsCursor.getString(
                                callLogsCursor.getColumnIndex(
                                    android.provider.CallLog.Calls.TYPE
                                )
                            )
                        )

                    val duration: String =
                        callLogsCursor.getString(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.DURATION))

                    Log.i("photoURI", "photoUri: $photoUri")

                    val formatter: SimpleDateFormat = SimpleDateFormat("dd/MM/YYYY, hh:mm a")
                    val dateString: String = formatter.format(Date(date))

                    if (photoUri != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Files.exists(
                            Paths.get(photoUri)
                        )
                    ) {
                        photo = BitmapFactory.decodeFile(photoUri)
                    }
                    Log.i("photo BITMAP", "photo : $photo")

                    viewModel.logsList?.add(
                        CallerModel(
                            name,
                            number,
                            dateString.split(",")[0],
                            dateString.split(",")[1],
                            callType,
                            photo,
                            duration
                        )
                    )
                    when (callType) {
                        CallLog.Calls.INCOMING_TYPE -> incoming+=1
                            CallLog.Calls.OUTGOING_TYPE->outgoing+=1
                            CallLog.Calls.MISSED_TYPE->missed+=1
                            CallLog.Calls.REJECTED_TYPE->unAnswered+=1
                    }
                } while (callLogsCursor.moveToNext())

            }
            callLogsCursor.close()
            DebugLog.d("SIZE:" + viewModel.logsList?.size.toString())
            GlobalScope.launch(context = Dispatchers.Main) {
                getDataBinding().tvMissedCallsCount.text =missed.toString()
                getDataBinding().tvDialCallsCount.text = outgoing.toString()
                getDataBinding().tvInComingCallCount.text = incoming.toString()
                getDataBinding().tvUnAnsweredCallsCount.text = unAnswered.toString()

            }


    }


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}
