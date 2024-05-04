package com.softwareallin1.aioscrm.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.network.HttpErrorCode
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.softwareallin1.aioscrm.utils.DebugLog
import com.softwareallin1.aioscrm.utils.sharedpref.PrefKey
import javax.inject.Inject


/**
 * Abstract base class for Fragments
 */
abstract class FragmentBase<V : ViewModelBase, DataBinding : ViewDataBinding> :
    FragmentBaseWrapper() {

    private lateinit var dataBinding: DataBinding
    private lateinit var mViewModel: V
    val viewModel: V get() = mViewModel

    @Inject
    lateinit var mPref: MyPreference

    /**
     * This is the abstract method by which we are generating the
     * Data binding with View from Single Screen.
     *
     */
    abstract fun getLayoutId(): Int

    /**
     * This is the generic method where we have to setup the toolbar from single place and
     * from all other extended fragment should have to manage the logic related to the toolbar
     * from this method
     */
    abstract fun setupToolbar()

    /**
     * This is the method from where we are initialized the
     * fragment specific data members and methods.
     */
    abstract fun initializeScreenVariables()

    protected abstract fun getViewModelClass(): Class<V>
    protected abstract fun getViewModelIsSharedViewModel(): Boolean

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.executePendingBindings()
        return dataBinding.root
    }

    private fun handleException(code: Int, message: String, messageCode: String) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, messageCode)
            HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, messageCode)
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            HttpErrorCode.SERVER_UNDER_MAINTENANCE.code -> serverUnderMaintenance(
                message, messageCode
            )

            else -> somethingWentWrong(message)
        }

    }

    override fun serverUnderMaintenance(message: String?, messageCode: String?) {
        MaterialAlertDialogBuilder(
            requireActivity(), R.style.CustomMaterialAlertDialog
        ).setTitle(R.string.app_name).setMessage(message)
            .setPositiveButton(R.string.lbl_ok) { _, _ ->
                activity?.finish()
            }.show()
    }


    fun getDataBinding() = dataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeScreenVariables()
        observeToolbar()
        setupToolbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mViewModel = if (getViewModelIsSharedViewModel()) {
            ViewModelProvider(requireActivity())[getViewModelClass()]
        } else {
            ViewModelProvider(this)[getViewModelClass()]
        }
        setUpProgressBar()
        setUpSnackBar()
        setUpHideKeyBoard()
        getFCMToken()
    }

    /**
     * This is generic method based on the MutableLive Data Concept where value changed with toolbar setup
     * will reflect and decide if value is true then it will display.
     */
    private fun observeToolbar() {
        viewModel.toolBarModel.observe(viewLifecycleOwner) {
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).toolBarManagement(it)
            }
        }
    }

    /**
     * This is generic method based on the MutableLive Data Concept where value changed with Progressbar
     * will reflect and decide if value is true then it will display.
     */
    private fun setUpProgressBar() {
        viewModel.progressBarDisplay.observe(this) { o: Boolean ->
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).displayProgress(o)
            }
        }
    }

    /**
     * This is generic method based on the MutableLive Data Concept where value changed with Snake bar
     * will reflect and display the message with Snake bar.
     */
    private fun setUpSnackBar() {
        viewModel.snackbarMessage.observe(this) { o: Any ->
            if (o is Int) {
                (activity as MainActivity).resources.getString(o).let { showSnackbar(it) }
            } else if (o is String) {
                showSnackbar(o)
            }

        }
    }


    /**
     * This is generic method based on the MutableLive Data Concept where value changed with Keyboard
     * will reflect and decide if value is false then it will hide the Keyboard.
     */
    private fun setUpHideKeyBoard() {
        viewModel.getHideKeyBoardEvent()
            .observe(this) { (requireActivity() as MainActivity).hideKeyboard() }
    }


    /**
     * This method is used to display the Snake Bar Toast message to user.
     *
     * @param message string to display.
     */
    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content)!!, message, Snackbar.LENGTH_SHORT
        )
        val view = snackbar.view
        val snackTV: TextView = view.findViewById(com.google.android.material.R.id.snackbar_text)
        snackTV.maxLines = 5
//        snackTV.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    /**
     * This is the generic method from where the message will display if something went wrong.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun somethingWentWrong(message: String?) {
        var msg = message
        if (message.isNullOrEmpty()) {
            msg = getString(R.string.internal_server_error_500)
        }
        viewModel.showSnackbarMessage(msg!!)
    }

    /**
     * This is the generic method from where the message will display if Internet connection not available.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun noInternet() {
        viewModel.showSnackbarMessage(getString(R.string.no_internet))
    }

    override fun onRetryClicked(retryButtonType: String) {
    }

    /**
     * This is the generic method from where the message will display if No Data Found.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun dataNotFound(message: String?, messageCode: String?) {
        message?.let { viewModel.showSnackbarMessage(it) }
    }

    /**
     * This is the generic method from where the message will display if user verified or not.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun verifyUser(message: String) {
        viewModel.showSnackbarMessage(message)
    }


    override fun newVersionFound() {
        //TODO::Redirect to play store
        //TODO::IN App Update
        var msg = "New Update Available"
        if (msg.isBlank()) {
            msg = getString(R.string.logout_401)
        }
        MaterialAlertDialogBuilder(
            requireActivity(), R.style.CustomMaterialAlertDialog
        ).setTitle(R.string.app_name).setMessage(msg)
            .setPositiveButton(R.string.lbl_ok) { dialog, _ ->
                dialog.dismiss()
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=${dataBinding.root.context.packageName}")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=${dataBinding.root.context.packageName}")
                        )
                    )
                }

            }.show()
    }

    /**
     * This is the generic method from where the message will display if user is unauthorized.
     * This will called based from the make Api called and decide the response with response code.
     */
    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        var msg = message
        if (msg.isNullOrBlank()) {
            msg = getString(R.string.logout_401)
        }
        MaterialAlertDialogBuilder(
            requireActivity(), R.style.CustomMaterialAlertDialog
        ).setTitle(R.string.app_name).setMessage(msg).setPositiveButton(R.string.lbl_ok) { _, _ ->
            mPref.clearAllData()
            activity?.finish()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }.show()
    }

    /**
     * Fetch FCM device token
     */
    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                DebugLog.e("Fetching FCM registration token failed : ${task.exception}")
                return@OnCompleteListener
            }
            val token = task.result
            mPref.setValueString(PrefKey.DEVICE_TOKEN, token!!)

            DebugLog.e(
                " FCM Device Token : ${
                    mPref.getValueString(
                        PrefKey.DEVICE_TOKEN, ""
                    )
                }"
            )
        })
    }


}