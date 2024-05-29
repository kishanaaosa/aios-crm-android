package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentPaymentsBinding
import com.softwareallin1.aioscrm.databinding.ItemPaymentBinding
import com.softwareallin1.aioscrm.ui.sales.model.Payment
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsFragment : FragmentBase<SalesViewModel, FragmentPaymentsBinding>() {
    override fun getLayoutId() = R.layout.fragment_payments

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<Payment> = arrayListOf()
        list.add(
            Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
            )
        )
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        list.add(Payment(
            "Vasole Automobile",
            "5000",
            "Cash",
            "INV-0001",
            "TID-0002",
            "16 Feb 2023",
        ))
        setUpCreditNotes(list)
    }

    private fun setUpCreditNotes(list: ArrayList<Payment>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvPayment.adapter = object :
                GenericRecyclerViewAdapter<Payment, ItemPaymentBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_payment

                override fun onBindData(
                    model: Payment,
                    position: Int,
                    dataBinding: ItemPaymentBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Payment, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvPayment.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}