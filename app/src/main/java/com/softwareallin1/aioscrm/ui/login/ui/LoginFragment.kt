package com.softwareallin1.aioscrm.ui.login.ui

import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentLoginBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.login.model.LoginResponse
import com.softwareallin1.aioscrm.ui.login.model.UserDetail
import com.softwareallin1.aioscrm.ui.login.viewmodel.LoginViewModel
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {
    override fun getLayoutId() = R.layout.fragment_login

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = false, title = "", isBottomNavVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        setUpObserver()
        //(activity as MainActivity).navigateToNextScreenThroughDirections(LoginFragmentDirections.actionLoginFragmentToFillAttendanceFragment())
    }

    private fun setUpObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Empty -> {

                }
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<LoginResponse>?> -> {
                    viewModel.showProgressBar(false)
                    storeUserDataAndNavigateToNextScreen(it.response?.data?.userDetail)
                }
            }
        })
    }

    private fun storeUserDataAndNavigateToNextScreen(userDetails: UserDetail?) {
        mPref.setValueString(PrefKey.USER__FIRST_NAME, userDetails?.firstname.toString())
        mPref.setValueString(PrefKey.USER_LAST_NAME, userDetails?.lastname.toString())
        mPref.setValueString(
            PrefKey.USER_NAME,
            userDetails?.firstname.toString() + " " + userDetails?.lastname.toString()
        )
        mPref.setValueString(PrefKey.STAFF_ID, userDetails?.staffid.toString())
        mPref.setValueString(PrefKey.EMAIL, userDetails?.email.toString())
        mPref.setValueString(PrefKey.PROFILE_IMAGE, userDetails?.profileImage.toString())
        mPref.setValueBoolean(PrefKey.IS_LOGIN, true)
        (activity as MainActivity).setupSideMenu()
        (activity as MainActivity).navigateToNextScreenThroughDirections(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}