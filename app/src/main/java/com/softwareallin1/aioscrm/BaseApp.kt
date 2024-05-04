package com.softwareallin1.aioscrm

import android.app.Application
import com.softwareallin1.aioscrm.utils.sharedpref.MyPreference
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApp : Application() {
    @Inject
    lateinit var mPref: MyPreference

    companion object {
        var myPreference: MyPreference? = null
        private var instance: BaseApp? = null

        fun applicationContext(): BaseApp {
            return instance as BaseApp
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        myPreference = mPref
    }
}