package com.softwareallin1.aioscrm.ui.leads.ui.tabs

import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentProfileBinding
import com.softwareallin1.aioscrm.ui.leads.viewmodel.LeadDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : FragmentBase<LeadDetailsViewModel, FragmentProfileBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun setupToolbar() {  }

    override fun initializeScreenVariables() {



    }

    override fun getViewModelClass(): Class<LeadDetailsViewModel> = LeadDetailsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true


}