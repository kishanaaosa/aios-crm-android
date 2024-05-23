package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentEstimatesBinding
import com.softwareallin1.aioscrm.databinding.ItemEstimateBinding
import com.softwareallin1.aioscrm.databinding.ItemProposalBinding
import com.softwareallin1.aioscrm.ui.sales.model.Estimate
import com.softwareallin1.aioscrm.ui.sales.model.Proposal
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EstimatesFragment : FragmentBase<SalesViewModel, FragmentEstimatesBinding>() {
    override fun getLayoutId() = R.layout.fragment_estimates

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<Estimate> = arrayListOf()
        setUpEstimates(list)
    }

    private fun setUpEstimates(list: ArrayList<Estimate>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvEstimate.adapter = object :
                GenericRecyclerViewAdapter<Estimate, ItemEstimateBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_estimate

                override fun onBindData(
                    model: Estimate,
                    position: Int,
                    dataBinding: ItemEstimateBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                 /*   for (item in model.tags ?: arrayListOf()) {
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
                    }*/
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Estimate, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvEstimate.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}