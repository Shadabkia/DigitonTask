package com.digitoon.task.di

import androidx.room.Room
import com.digitoon.task.DigitoonApplication
import com.digitoon.task.data.db.DigitoonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: DigitoonApplication) =
        Room.databaseBuilder(
            app.applicationContext,
            DigitoonDataBase::class.java, "taghche-database"
        ).build()
}