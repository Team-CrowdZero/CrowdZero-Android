package com.gdg.crowdzero_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CrowdZero : Application() {
    override fun onCreate() {
        super.onCreate()
        setTimber()
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }

}