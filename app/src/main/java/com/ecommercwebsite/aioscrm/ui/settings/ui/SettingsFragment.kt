package com.ecommercwebsite.aioscrm.ui.settings.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.databinding.FragmentSplashBinding
import com.ecommercwebsite.aioscrm.ui.settings.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : FragmentBase<SettingsViewModel,FragmentSplashBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_settings
    override fun setupToolbar() {

    }

    override fun initializeScreenVariables() {

    }

    override fun getViewModelClass(): Class<SettingsViewModel> = SettingsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false
}