package com.softwareallin1.aioscrm.ui.autocall.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.DialogFragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentChangeLeadStatusPopUpBinding
import com.softwareallin1.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeLeadStatusPopUpFragment : DialogFragmentBase<AutoCallViewModel,FragmentChangeLeadStatusPopUpBinding>() {
    override fun getLayoutId() = R.layout.fragment_change_lead_status_pop_up

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        getDataBinding().btnChangeStatus.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun getViewModelClass(): Class<AutoCallViewModel>  = AutoCallViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}