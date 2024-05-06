package com.softwareallin1.aioscrm.ui.autocall.ui

import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.DialogFragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentAddNotesPopUpBinding
import com.softwareallin1.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNotesPopUpFragment :
    DialogFragmentBase<AutoCallViewModel, FragmentAddNotesPopUpBinding>() {

    override fun getLayoutId() = R.layout.fragment_add_notes_pop_up

    override fun setupToolbar() {
        //TODO("Not yet implemented")
    }

    override fun initializeScreenVariables() {
        //TODO("Not yet implemented")
        getDataBinding().btnAddNote.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun getViewModelClass(): Class<AutoCallViewModel> = AutoCallViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}