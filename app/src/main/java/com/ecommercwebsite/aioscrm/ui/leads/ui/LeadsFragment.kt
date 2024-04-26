package com.ecommercwebsite.aioscrm.ui.leads.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ecommercwebsite.aioscrm.MainActivity
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.base.FragmentBase
import com.ecommercwebsite.aioscrm.base.ToolbarModel
import com.ecommercwebsite.aioscrm.bind.GenericRecyclerViewAdapter
import com.ecommercwebsite.aioscrm.databinding.FragmentLeadsBinding
import com.ecommercwebsite.aioscrm.databinding.ItemLeadsBinding
import com.ecommercwebsite.aioscrm.network.ResponseData
import com.ecommercwebsite.aioscrm.network.ResponseHandler
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsList
import com.ecommercwebsite.aioscrm.ui.leads.model.LeadsResponse
import com.ecommercwebsite.aioscrm.ui.leads.viewmodel.LeadsViewModel
import com.ecommercwebsite.aioscrm.utils.CommonFunctionHelper
import com.ecommercwebsite.aioscrm.utils.CommonFunctionHelper.openWhatsAppChat
import com.ecommercwebsite.aioscrm.utils.permissions.Permission
import com.ecommercwebsite.aioscrm.utils.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LeadsFragment : FragmentBase<LeadsViewModel, FragmentLeadsBinding>() {
    private val permissionManager = PermissionManager.from(this)

    override fun getLayoutId(): Int = R.layout.fragment_leads

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Leads",
                isBackButtonVisible = false,
                isBottomNavVisible = true,
                isNotificationVisible = true,
                isMenuVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        viewModel.getLeads()
        setUpObserver()
        checkPermissions()
    }

    private fun checkPermissions() {
        permissionManager
            .request(
                Permission.CallPhone
            )
            .rationale(getString(R.string.calling))
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    // viewModel.showSnackbarMessage("Permission Granted")
                } else {
                    // viewModel.showSnackbarMessage("Permission Denied")
                }
            }
    }

    private fun setUpObserver() {
        viewModel.leadsResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                    showNoDataFound()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<LeadsResponse>?> -> {
                    viewModel.showProgressBar(false)
                    setUpLeads(it.response?.data?.leads)
                }
            }
        })
    }

    private fun setUpLeads(leads: ArrayList<LeadsList>?) {
        viewModel.totalRecords.value = leads?.size.toString()
        if (leads?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvLeads.adapter = object :
                GenericRecyclerViewAdapter<LeadsList, ItemLeadsBinding>(
                    requireContext(),
                    leads
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_leads

                override fun onBindData(
                    model: LeadsList,
                    position: Int,
                    dataBinding: ItemLeadsBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.ivWhatsApp.setOnClickListener {
                        openWhatsAppChat(requireContext(), model.phonenumber.toString())
                    }
                    dataBinding.ivCall.setOnClickListener {
                        makeCall(model.phonenumber)
                    }
                    dataBinding.ivMail.setOnClickListener {
                        makeMail(model.email)
                    }
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: LeadsList, position: Int) {
                }
            }
        }
    }


    private fun makeMail(email: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:")) // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, email.toString())
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun makeCall(phoneNumber: String?) {
        if (phoneNumber != "") {
            try {
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:$phoneNumber")
                requireContext().startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Invalid PhoneNumber", Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.showSnackbarMessage("Invalid PhoneNumber")
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvLeads.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<LeadsViewModel> = LeadsViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}