package com.digitoon.task

import android.app.Application
import com.google.gson.internal.GsonBuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DigitoonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}