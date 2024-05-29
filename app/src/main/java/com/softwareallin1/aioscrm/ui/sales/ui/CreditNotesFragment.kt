package com.softwareallin1.aioscrm.ui.sales.ui

import android.view.View
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.bind.GenericRecyclerViewAdapter
import com.softwareallin1.aioscrm.databinding.FragmentCreditNotesBinding
import com.softwareallin1.aioscrm.databinding.ItemCreditNoteBinding
import com.softwareallin1.aioscrm.ui.sales.model.CreditNote
import com.softwareallin1.aioscrm.ui.sales.viewmodel.SalesViewModel
import com.softwareallin1.aioscrm.utils.CommonFunctionHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditNotesFragment : FragmentBase<SalesViewModel, FragmentCreditNotesBinding>() {
    override fun getLayoutId() = R.layout.fragment_credit_notes

    override fun setupToolbar() {
    }

    override fun initializeScreenVariables() {
        val list: ArrayList<CreditNote> = arrayListOf()
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        list.add(
            CreditNote(
                "Open",
                " Vsol Automobile",
                "5000",
                "New Project",
                "7000",
                "New Project",
                "16 Feb 2023"
            )
        )
        setUpCreditNotes(list)
    }

    private fun setUpCreditNotes(list: ArrayList<CreditNote>?) {
        if (list?.size == 0) {
            showNoDataFound()
        } else {
            getDataBinding().rvCreditNote.adapter = object :
                GenericRecyclerViewAdapter<CreditNote, ItemCreditNoteBinding>(
                    requireContext(),
                    list
                ) {
                override val layoutResId: Int
                    get() = R.layout.item_credit_note

                override fun onBindData(
                    model: CreditNote,
                    position: Int,
                    dataBinding: ItemCreditNoteBinding
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

                override fun onItemClick(model: CreditNote, position: Int) {
                }
            }
        }
    }

    private fun showNoDataFound() {
        getDataBinding().clNoDataFound.visibility = View.VISIBLE
        getDataBinding().rvCreditNote.visibility = View.GONE
    }

    override fun getViewModelClass(): Class<SalesViewModel> = SalesViewModel::class.java

    override fun getViewModelIsSharedViewModel() = true

}