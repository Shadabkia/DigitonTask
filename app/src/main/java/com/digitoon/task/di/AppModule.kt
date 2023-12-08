package com.digitoon.task.di

import android.content.Context
import com.digitoon.task.DigitoonApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): DigitoonApplication {
        return app as DigitoonApplication
    }

    @Singleton
    @Provides
    fun provideGson() = Gson()

}