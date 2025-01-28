package com.gdg.crowdzero_android

import android.app.Application
import com.gdg.crowdzero_android.BuildConfig.NAVER_CLIENT_ID
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CrowdZero : Application() {
    override fun onCreate() {
        super.onCreate()
        setTimber()
        initNaverMapSdk()
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initNaverMapSdk() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(NAVER_CLIENT_ID)
    }
}
