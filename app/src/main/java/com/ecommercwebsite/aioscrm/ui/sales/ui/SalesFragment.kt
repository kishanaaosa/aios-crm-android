package com.ecommercwebsite.aioscrm.ui.sales.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.databinding.FragmentSplashBinding
import com.ecommercwebsite.aioscrm.ui.sales.viewmodel.SalesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalesFragment : FragmentBase<SalesViewModel,FragmentSplashBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_leads

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}