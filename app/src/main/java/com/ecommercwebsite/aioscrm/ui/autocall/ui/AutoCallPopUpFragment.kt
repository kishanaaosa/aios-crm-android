package com.ecommercwebsite.aioscrm.ui.autocall.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ecommercwebsite.aioscrm.R
import com.ecommercwebsite.aioscrm.databinding.FragmentAutoCallPopUpBinding
import com.ecommercwebsite.aioscrm.ui.autocall.viewmodel.AutoCallViewModel
import com.ecommercwebsite.aioscrm.utils.CallStateFinder
import com.ecommercwebsite.aioscrm.utils.CommonCallStateFinder
import com.ecommercwebsite.aioscrm.utils.DebugLog
import com.ecommercwebsite.aioscrm.utils.sharedpref.PrefKey
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AutoCallPopUpFragment : DialogFragment(), CommonCallStateFinder {
    lateinit var viewModel: AutoCallViewModel
    lateinit var dataBinding: FragmentAutoCallPopUpBinding
    private lateinit var countDownTimer: CountDownTimer
    private var timerRunning = false
    lateinit var callStateFinder: CallStateFinder

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callStateFinder = CallStateFinder(requireContext(), this)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStart() {
        super.onStart()
        callStateFinder.startCallStateListener()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStop() {
        super.onStop()
        callStateFinder.stopCallStateListener()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_auto_call_pop_up, container, false)
        return dataBinding.root
    }

    override fun getTheme(): Int {
        return R.style.MyBottomSheetDialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AutoCallViewModel::class.java]
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()

        dataBinding.btnStop.setOnClickListener {
            viewModel.myPreference.setValueBoolean(PrefKey.IS_AUTO_CALLING, false)
            viewModel.isCallFinished.value = false
            this.dismiss()
            viewModel.getAutoCallLeads()
        }
        dataBinding.btnNext.setOnClickListener {
            viewModel.isCallFinished.value = false
            this.dismiss()
            viewModel.getAutoCallLeads()
        }
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
                //makeCall(viewModel.selectedLead.value?.phonenumber)
                makeCall("9726540727")
            }
        }

        countDownTimer.start()
        timerRunning = true
    }

    private fun makeCall(phoneNumber: String?) {
        if (phoneNumber != "") {
            try {
                viewModel.isCallStarted.value = true
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:$phoneNumber")
                requireContext().startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Invalid PhoneNumber", Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.isCallStarted.value = false
            viewModel.showSnackbarMessage("Invalid PhoneNumber")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (timerRunning) {
            countDownTimer.cancel()
        }
    }

    override fun onCallStateChanged(state: Int) {
        when (state) {
            TelephonyManager.CALL_STATE_IDLE -> {
                if (viewModel.isCallStarted.value == true) {
                    dataBinding.tvAutoCallTimer.text = "Call Finished"
                    viewModel.isCallStarted.value = false
                    viewModel.isCallFinished.value = true
                }
                DebugLog.print("::::: call state idle")
            }

            TelephonyManager.CALL_STATE_RINGING -> {
                DebugLog.print("::::: call state ringing")
            }

            TelephonyManager.CALL_STATE_OFFHOOK -> {
                DebugLog.print("::::: call state offhook")
            }
        }
    }

}