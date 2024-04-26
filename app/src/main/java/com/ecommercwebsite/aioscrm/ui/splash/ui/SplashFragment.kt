package com.ecommercwebsite.aioscrm.ui.splash.ui

import android.annotation.SuppressLint
import android.os.Build
import android.provider.Settings
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.base.ViewModelBase
import com.ecommercwebsite.aioscrm.databinding.FragmentSplashBinding
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : FragmentBase<ViewModelBase, FragmentSplashBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = false, title = "", isBottomNavVisible = false
            )
        )
    }

    override fun getViewModelClass(): Class<ViewModelBase> = ViewModelBase::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false


    @SuppressLint("HardwareIds")
    override fun initializeScreenVariables() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        mPref.setValueString(
            PrefKey.DEVICE_ID,
            Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.ANDROID_ID)
        )
        mPref.setValueString(
            PrefKey.OS_TYPE, "${Build.BRAND} -- ${Build.MODEL}"
        ) // Device name and model set in os type

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()
        rotateLogo()
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(2000)
            navigateToScreen()
        }
    }

    private fun rotateLogo() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.rotate)
        getDataBinding().imgAnimSplash?.startAnimation(animation)
    }

    private fun navigateToScreen() {
        if (mPref.getValueBoolean(PrefKey.IS_LOGIN, false) == true) {
            (activity as MainActivity).navigateToNextScreenThroughDirections(
                SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            )
        } else {
            (activity as MainActivity).navigateToNextScreenThroughDirections(
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            )
        }
    }

}