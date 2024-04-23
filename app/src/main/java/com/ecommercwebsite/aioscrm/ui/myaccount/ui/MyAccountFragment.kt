package com.ecommercwebsite.aioscrm.ui.myaccount.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.databinding.FragmentMyAccountBinding
import com.ecommercwebsite.aioscrm.ui.myaccount.viewmodel.MyAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAccountFragment : FragmentBase<MyAccountViewModel,FragmentMyAccountBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_my_account

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<MyAccountViewModel> = MyAccountViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}