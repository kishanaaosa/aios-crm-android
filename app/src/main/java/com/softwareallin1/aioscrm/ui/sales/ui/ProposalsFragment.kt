package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentProposalsBinding
import com.softwareallin1.aioscrm.databinding.ItemProposalBinding
import com.softwareallin1.aioscrm.ui.sales.model.Proposal
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProposalsFragment : FragmentBase<SalesViewModel, FragmentProposalsBinding>() {
    override fun getLayoutId() = R.layout.fragment_proposals

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<Proposal> = arrayListOf()
        list.add(
            Proposal(
                "Proposal Title", "Sent", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Declined", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Accepted", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Revised", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Draft", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Sent", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Accepted", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Draft", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Revised", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Declined", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Sent", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        list.add(
            Proposal(
                "Proposal Title", "Accepted", "Josh Last", "2 Feb 2023", "16 Feb 2023",
                arrayListOf("Appointment", "Interview", "Shaps","Closed")
            )
        )
        //setUpProposals(list)
        setUpProposals(arrayListOf())
    }

    private fun setUpProposals(list: ArrayList<Proposal>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvProposal.adapter = object :
                GenericRecyclerViewAdapter<Proposal, ItemProposalBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_proposal

                override fun onBindData(
                    model: Proposal,
                    position: Int,
                    dataBinding: ItemProposalBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    for (item in model.tags ?: arrayListOf()) {
                        val chip = Chip(requireContext())
                        chip.text = item
                        chip.chipStrokeWidth = resources.getDimension(com.intuit.sdp.R.dimen._1sdp)
                        chip.chipBackgroundColor =
                            ContextCompat.getColorStateList(context, R.color.white)
                        chip.chipStrokeColor = ContextCompat.getColorStateList(
                            context,
                            R.color.colorGradientStartC73B1C
                        )
                        chip.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorGradientStartC73B1C
                            )
                        )

                        chip.chipCornerRadius = resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
                        dataBinding.chipGroup.addView(chip)
                    }
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Proposal, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvProposal.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}