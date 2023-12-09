package com.digitoon.task

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DigitoonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}