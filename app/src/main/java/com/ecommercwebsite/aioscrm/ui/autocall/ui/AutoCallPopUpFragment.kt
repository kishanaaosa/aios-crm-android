package com.ecommercwebsite.aioscrm.ui.autocall.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.databinding.FragmentAutoCallBinding
import com.ecommercwebsite.aioscrm.databinding.FragmentAutoCallPopUpBinding
import com.ecommercwebsite.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoCallPopUpFragment : BottomSheetDialogFragment() {
    lateinit var viewModel: AutoCallViewModel
    lateinit var dataBinding: FragmentAutoCallPopUpBinding
    private lateinit var countDownTimer: CountDownTimer
    private var timerRunning = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_auto_call_pop_up, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AutoCallViewModel::class.java]
        dataBinding.viewModel = viewModel
        //viewmodel.initVariables()
        viewModel.onCancel.observe(this, Observer {
            this.dismiss()
        })
        //other code
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                    dataBinding.tvAutoCallTimer.text = "Call Start in $secondsLeft Second"
            }

            override fun onFinish() {
                dataBinding.tvAutoCallTimer.text = "Call Started"
                timerRunning = false
            }
        }

        countDownTimer.start()
        timerRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timerRunning) {
            countDownTimer.cancel()
        }
    }

}