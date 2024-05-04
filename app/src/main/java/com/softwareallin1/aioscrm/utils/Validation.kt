package com.softwareallin1.aioscrm.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object Validation {

    /**
     * method is used for checking valid email id format.
     *
     * @param email email address as String
     * @return boolean true for valid false for invalid
     */
    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    /**
     * method is used for checking valid email id format.
     *
     * @param email email address as String
     * @return boolean true for valid false for invalid
     */
    fun isEmailValidWithOptional(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            true
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    /**
     * method is used for checking password is in valid format.
     *
     * @param password password as String
     *
     * 1 Upper case, 1 Lower case, 1 Special Characters and 6-12 characters
     * @return boolean true for valid false for invalid
     */
    fun isValidPassword(password: String): Boolean {
        DebugLog.e(password)
//        val patn = "^(^(?=.*?[A-Z])(?=.*?[a-z]))((?=.*?[0-9])|(?=.*?[#?!@\$%^&*-])).{8,}\$"
        val patn =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\$&+,:;=?@#|_<>.^*()%!-])[A-Za-z\\d\$&+,:;=?@#|_<>.^*()%!-]{8,}"
        val pattern = Pattern.compile(patn)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }


    /**
     * method is used for checking if string is empty or not.
     *
     * @param mString as String
     * @return boolean true if [mString] is notnull.
     */
    fun isNotNull(mString: String?): Boolean {
        return when {
            mString == null -> {
                false
            }

            mString.equals("", ignoreCase = true) -> {
                false
            }

            mString.equals("N/A", ignoreCase = true) -> {
                false
            }

            mString.equals("[]", ignoreCase = true) -> {
                false
            }

            mString.equals("null", ignoreCase = true) -> {
                false
            }

            mString.isEmpty() -> {
                false
            }

            else -> !mString.equals("{}", ignoreCase = true)
        }
    }


    /**
     * Method is used for checking if mobile number is valid or not
     */

    fun isValidPhoneNumber(s: String): Boolean {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        //val p = Pattern.compile("(0/91)?[7-9][0-9]{9}")

        val p = Pattern.compile("[0-9]{10}")

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        val m = p.matcher(s)
        return m.find() && m.group() == s
    }

    /**
     * Method is used for checking if mobile number is valid or not
     */

    fun isValidOtp(s: String): Boolean {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        //val p = Pattern.compile("(0/91)?[7-9][0-9]{9}")

        val p = Pattern.compile("[0-9]{6}")

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        val m = p.matcher(s)
        return m.find() && m.group() == s
    }

}