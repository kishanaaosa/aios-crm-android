package com.softwareallin1.aioscrm.ui.sales.ui

import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentCreditNotesBinding
import com.softwareallin1.aioscrm.databinding.FragmentProductBinding
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditNotesFragment : FragmentBase<SalesViewModel, FragmentCreditNotesBinding>() {
    override fun getLayoutId() = R.layout.fragment_credit_notes

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}