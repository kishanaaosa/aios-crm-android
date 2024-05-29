package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentInvoicesBinding
import com.softwareallin1.aioscrm.databinding.ItemInvoiceBinding
import com.softwareallin1.aioscrm.ui.sales.model.Invoice
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvoicesFragment : FragmentBase<SalesViewModel, FragmentInvoicesBinding>() {
    override fun getLayoutId() = R.layout.fragment_invoices

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<Invoice> = arrayListOf()
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        list.add(
            Invoice(
                "Paid",
                "Vasol Automobile",
                "5000",
                "New Project",
                "500",
                arrayListOf("Appointment", "Interview", "Shaps", "Closed"),
                "2 Feb 2023",
                "2 Feb 2023"
            )
        )
        setUpCreditNotes(list)
    }

    private fun setUpCreditNotes(list: ArrayList<Invoice>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvInvoice.adapter = object :
                GenericRecyclerViewAdapter<Invoice, ItemInvoiceBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_invoice

                override fun onBindData(
                    model: Invoice,
                    position: Int,
                    dataBinding: ItemInvoiceBinding
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

                override fun onItemClick(model: Invoice, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvInvoice.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}