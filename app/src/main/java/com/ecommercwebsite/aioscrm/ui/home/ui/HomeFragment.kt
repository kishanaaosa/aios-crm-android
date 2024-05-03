package com.ecommercwebsite.aioscrm.ui.home.ui

import androidx.lifecycle.Observer
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentHomeBinding
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.home.model.CheckAttendanceResponse
import com.ecommercwebsite.aioscrm.ui.home.viewmodel.HomeViewModel
import com.ecommercwebsite.aioscrm.utils.permissions.Permission
import com.ecommercwebsite.aioscrm.utils.permissions.PermissionManager
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBase<HomeViewModel, FragmentHomeBinding>() {
    private val permissionManager = PermissionManager.from(this)

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
    }

    private fun checkPermissions() {
        permissionManager
            .request(
                Permission.PostNotification
            )
            .rationale(getString(R.string.post_notification))
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    // viewModel.showSnackbarMessage("Permission Granted")
                } else {
                    // viewModel.showSnackbarMessage("Permission Denied")
                }
            }
    }


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}
