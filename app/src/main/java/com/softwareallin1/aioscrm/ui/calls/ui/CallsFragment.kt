package com.softwareallin1.aioscrm.ui.calls.ui

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import com.example.android_practical.ui.home.CallerModel
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentCallsBinding
import com.softwareallin1.aioscrm.databinding.ItemCallsBinding
import com.softwareallin1.aioscrm.ui.calls.viewmodel.CallsViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import com.softwareallin1.aioscrm.utils.DebugLog
import com.softwareallin1.aioscrm.utils.permissions.Permission
import com.softwareallin1.aioscrm.utils.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class CallsFragment : FragmentBase<CallsViewModel, FragmentCallsBinding>() {
    private val permissionManager = PermissionManager.from(this)

    override fun getLayoutId(): Int = R.layout.fragment_calls

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Calls",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        viewModel.initVariables()
        //viewModel.getLeadsWisePhoneCallLog()
        checkPermissions()
        //setUpObserver()
        getDataBinding().viewModel = viewModel
    }

    private fun checkPermissions() {
        permissionManager
            .request(
                Permission.ReadWriteLog
            )
            .rationale(getString(R.string.post_notification))
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    readCallLogs()
                    // viewModel.showSnackbarMessage("Permission Granted")
                } else {
                    // viewModel.showSnackbarMessage("Permission Denied")
                }
            }
    }

    private fun readCallLogs() {
        viewModel.showProgressBar(true)
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
                    Integer.parseInt(callLogsCursor.getString(callLogsCursor.getColumnIndex(android.provider.CallLog.Calls.TYPE)))

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

                if (viewModel.logsList.size <= 50) {
                    viewModel.logsList.add(
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
                }
            } while (callLogsCursor.moveToNext())

        }
        callLogsCursor.close()
        DebugLog.d("SIZE:" + viewModel.logsList?.size.toString())
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(1000)
            setUpRecyclerViews()
        }
    }


    private fun setUpRecyclerViews() {
        viewModel.totalRecords.value = viewModel.logsList?.size.toString()
        viewModel.showProgressBar(false)
        getDataBinding().rvCalls.adapter = object : GenericRecyclerViewAdapter<
                CallerModel?, ItemCallsBinding>(
            requireContext(),
            viewModel.logsList
        ) {
            override val layoutResId: Int
                get() = R.layout.item_calls

            override fun onItemClick(model: CallerModel?, position: Int) {

            }

            override fun onBindData(
                model: CallerModel?,
                position: Int,
                dataBinding: ItemCallsBinding
            ) {
                CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                dataBinding.model = model

                val img = when (model?.callType) {
                    android.provider.CallLog.Calls.INCOMING_TYPE -> R.drawable.ic_incoming_small
                    android.provider.CallLog.Calls.OUTGOING_TYPE -> R.drawable.ic_dial_small
                    android.provider.CallLog.Calls.MISSED_TYPE -> R.drawable.ic_missed_small
                    android.provider.CallLog.Calls.REJECTED_TYPE -> R.drawable.ic_unanswered_small
                    else -> R.drawable.ic_incoming
                }
                dataBinding.icCallType.setImageDrawable(viewModel.resourcesProvider.getDrawable(img))
                dataBinding.tvDuration.text = model?.duration?.toInt()
                    ?.let { CommonFunctionHelper.formatSecondsToMinutes(it) }

                dataBinding.executePendingBindings()
            }

        }

    }

    /*  private fun setUpObserver() {
          viewModel.callLigListResponse.observe(viewLifecycleOwner, Observer {
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
                      showNoDataFound()
                  }

                  is ResponseHandler.OnSuccessResponse<ResponseData<CallLogListResponse>?> -> {
                      viewModel.showProgressBar(false)
                      setUpCalls(it.response?.data?.callLogList)
                  }
              }
          })    }*/

    /*
        private fun setUpCalls(callLogList: ArrayList<CallLog>?) {
            if (callLogList?.size == 0) {
                showNoDataFound()
            } else {
                getDataBinding().rvCalls.adapter = object :
                    GenericRecyclerViewAdapter<CallLog, ItemCallsBinding>(
                        requireContext(),
                        callLogList
                    ) {
                    override val layoutResId: Int
                        get() = R.layout.item_calls

                    override fun onBindData(
                        model: CallLog,
                        position: Int,
                        dataBinding: ItemCallsBinding
                    ) {
                        CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                        dataBinding.model = model
                        dataBinding.executePendingBindings()
                    }

                    override fun onItemClick(model: CallLog, position: Int) {
                    }
                }
            }
        }
    */

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvCalls.visibility = View.GONE
    }


    override fun getViewModelClass(): Class<CallsViewModel> = CallsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}