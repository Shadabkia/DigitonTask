package com.digitoon.task.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.digitoon.task.data.db.dao.MovieDao
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.data.db.entity.Converters

@Database(
    entities = [MovieLocal::class], version = 1
)
@TypeConverters(Converters::class)
abstract class DigitoonDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}