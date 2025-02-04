package com.softwareallin1.aioscrm.base

import androidx.fragment.app.DialogFragment
import com.softwareallin1.aioscrm.network.HttpErrorCode

/**
 * FragmentWrapper class to handle Web service failure case
 */
abstract class DialogFragmentBaseWrapper : DialogFragment() {
    abstract fun somethingWentWrong(message: String?)

    abstract fun noInternet()
    abstract fun serverUnderMaintenance(message: String?, messageCode: String?)

    abstract fun onRetryClicked(retryButtonType: String)

    abstract fun dataNotFound(message: String?, messageCode: String?)
    abstract fun verifyUser(message: String)

    abstract fun newVersionFound()

    abstract fun unAuthorizationUser(message: String?, messageCode: String?)

    open fun httpFailedHandler(code: Int, message: String?, messageCode: String?) {
        handleException(code, message, messageCode)
    }

    private fun handleException(code: Int, message: String?, messageCode: String?) {
        when (code) {
            HttpErrorCode.EMPTY_RESPONSE.code -> dataNotFound(message, messageCode)
            HttpErrorCode.NEW_VERSION_FOUND.code -> newVersionFound()
            HttpErrorCode.UNAUTHORIZED.code -> unAuthorizationUser(message, messageCode)
            HttpErrorCode.NO_CONNECTION.code -> noInternet()
            HttpErrorCode.SERVER_UNDER_MAINTENANCE.code -> serverUnderMaintenance(
                message,
                messageCode
            )

            else -> somethingWentWrong(message)
        }
    }

}