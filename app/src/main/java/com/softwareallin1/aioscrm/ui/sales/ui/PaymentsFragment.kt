package com.softwareallin1.aioscrm.ui.sales.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentPaymentsBinding
import com.softwareallin1.aioscrm.databinding.FragmentProductBinding
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsFragment : FragmentBase<SalesViewModel, FragmentPaymentsBinding>() {
    override fun getLayoutId() = R.layout.fragment_payments

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}