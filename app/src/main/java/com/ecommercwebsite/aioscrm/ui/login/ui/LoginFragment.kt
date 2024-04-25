package com.ecommercwebsite.aioscrm.ui.login.ui

import androidx.lifecycle.Observer
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentLoginBinding
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.login.model.LoginResponse
import com.ecommercwebsite.aioscrm.ui.login.viewmodel.LoginViewModel
import com.ecommercwebsite.aioscrm.utils.PrefKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {
    override fun getLayoutId() = R.layout.fragment_login

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = false, title = "", isBottomNavVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        setUpClickObserver()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
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

                is ResponseHandler.OnSuccessResponse<ResponseData<LoginResponse>?> -> {
                    viewModel.showProgressBar(false)
                    (activity as MainActivity).navigateToNextScreenThroughDirections(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    )
                }
            }
        })
    }

    private fun setUpClickObserver() {
        viewModel.onLogin.observe(viewLifecycleOwner) {
            mPref.setValueBoolean(PrefKey.IS_LOGIN, true)
            (activity as MainActivity).setupSideMenu()
            (activity as MainActivity).navigateToNextScreenThroughDirections(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}