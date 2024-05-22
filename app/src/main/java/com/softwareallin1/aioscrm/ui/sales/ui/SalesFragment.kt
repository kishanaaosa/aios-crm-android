package com.softwareallin1.aioscrm.ui.sales.ui

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.databinding.FragmentSalesBinding
import com.softwareallin1.aioscrm.ui.sales.adapter.SalesTabsViewPagerAdapter
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.ui.todo.adapter.ToDoTabsViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalesFragment : FragmentBase<SalesViewModel,FragmentSalesBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_sales

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.colorGradientEnd, false)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = "Sales",
                isBackButtonVisible = true,
                isBottomNavVisible = false,
                isNotificationVisible = false,
                isMenuVisible = false
            )
        )
    }

    override fun initializeScreenVariables() {
        setUpViewpager()
    }

    private fun setUpViewpager() {
        val fragmentArrayList: ArrayList<Fragment> = arrayListOf()
        val fragmentNameArrayList: ArrayList<String> = arrayListOf()

        fragmentArrayList.add(ProductFragment())
        fragmentArrayList.add(ProposalsFragment())
        fragmentArrayList.add(EstimatesFragment())
        fragmentArrayList.add(InvoicesFragment())
        fragmentArrayList.add(CreditNotesFragment())
        fragmentArrayList.add(PaymentsFragment())

        fragmentNameArrayList.add("Products")
        fragmentNameArrayList.add("Proposals")
        fragmentNameArrayList.add("Estimates")
        fragmentNameArrayList.add("Invoices")
        fragmentNameArrayList.add("Credit Notes")
        fragmentNameArrayList.add("Payments")

        val adapter =
            SalesTabsViewPagerAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                fragmentArrayList
            )
        getDataBinding().vpSales.adapter = adapter

        TabLayoutMediator(getDataBinding().tbSales, getDataBinding().vpSales) { tab, position ->
            tab.text = fragmentNameArrayList[position]
        }.attach()
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}