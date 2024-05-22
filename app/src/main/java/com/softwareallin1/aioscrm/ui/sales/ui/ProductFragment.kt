package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentProductBinding
import com.softwareallin1.aioscrm.databinding.ItemProductBinding
import com.softwareallin1.aioscrm.ui.sales.model.Product
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : FragmentBase<SalesViewModel, FragmentProductBinding>() {
    override fun getLayoutId() = R.layout.fragment_product

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<Product> = arrayListOf()
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_10.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_11.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_12.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_1.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_2.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_3.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_4.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_5.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_6.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_7.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_8.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_10.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_11.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_12.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_1.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_2.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_3.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_4.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_5.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_6.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_7.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_8.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_10.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_11.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_12.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_1.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_2.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_3.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_4.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_5.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_6.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_7.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_8.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_10.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_11.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_12.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_1.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_2.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_3.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_4.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_5.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_6.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_7.png",
                "25$"
            )
        )
        list.add(
            Product(
                "Demo Product - 1",
                "https://onyxappstudio.com/apps/coin-master/images/village/lvl_8.png",
                "25$"
            )
        )
        setUpProduct(list)
    }

    private fun setUpProduct(list: ArrayList<Product>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvProduct.adapter = object :
                GenericRecyclerViewAdapter<Product, ItemProductBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_product

                override fun onBindData(
                    model: Product,
                    position: Int,
                    dataBinding: ItemProductBinding
                ) {
                    CommonFunctionHelper.setFadeAnimation(dataBinding.root)
                    dataBinding.model = model
                    dataBinding.executePendingBindings()
                }

                override fun onItemClick(model: Product, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvProduct.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}