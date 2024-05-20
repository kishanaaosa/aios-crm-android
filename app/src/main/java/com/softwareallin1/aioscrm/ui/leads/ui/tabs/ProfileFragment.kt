package com.softwareallin1.aioscrm.ui.leads.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentProfileBinding
import com.softwareallin1.aioscrm.ui.leads.viewmodel.ProfileDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : FragmentBase<ProfileDetailViewModel, FragmentProfileBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun setupToolbar() {  }

    override fun initializeScreenVariables() {



    }

    override fun getViewModelClass(): Class<ProfileDetailViewModel> = ProfileDetailViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = true


}