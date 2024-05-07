package com.softwareallin1.aioscrm.ui.autocall.ui

import androidx.lifecycle.Observer
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.DialogFragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentAddNotesPopUpBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.autocall.model.AddNoteOnLeadResponse
import com.softwareallin1.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import com.softwareallin1.aioscrm.utils.Validation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNotesPopUpFragment :
    DialogFragmentBase<AutoCallViewModel, FragmentAddNotesPopUpBinding>() {

    override fun getLayoutId() = R.layout.fragment_add_notes_pop_up

    override fun setupToolbar() {
        //TODO("Not yet implemented")
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        setUpObserver()
    }

    private fun setUpObserver() {
        getDataBinding().btnAddNote.setOnClickListener {
            if (Validation.isNotNull(viewModel.note.value.toString().trim())) {
                viewModel.addNotesOnLead()
            } else {
                viewModel.showSnackbarMessage("Pleas enter a note.")
            }
        }
        viewModel.addNoteOnLeadResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Empty -> {

                }
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<AddNoteOnLeadResponse>?> -> {
                    viewModel.showProgressBar(false)
                    //viewModel.showSnackbarMessage(it.response?.meta?.message.toString())
                    viewModel.isNotAdded.value = true
                }
            }
        })
        viewModel.isNotAdded.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.addNoteOnLeadResponse.value = ResponseHandler.Empty
                dialog?.dismiss()
            }
        }
    }

    override fun getViewModelClass(): Class<AutoCallViewModel> = AutoCallViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}