package io.minoro75.demoweatherapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        Timber.plant()
        super.onCreate()
    }
}
