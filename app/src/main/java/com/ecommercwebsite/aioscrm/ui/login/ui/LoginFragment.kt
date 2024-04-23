package com.ecommercwebsite.aioscrm.ui.login.ui

import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.databinding.FragmentLoginBinding
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