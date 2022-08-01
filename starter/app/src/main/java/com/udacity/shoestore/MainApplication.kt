package com.udacity.shoestore

import android.app.Application
import com.google.android.material.color.DynamicColors
import timber.log.Timber

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // plant timber for logging
        Timber.plant(Timber.DebugTree())
    }
}