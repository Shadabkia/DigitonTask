package com.digitoon.task.data.db.dao

import androidx.room.*
import com.digitoon.task.data.db.entity.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun add(movie: MovieLocal): Long

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(movieList: List<MovieLocal?>)

    @Update
    suspend fun updateMovie(movie: MovieLocal)

    @Query("SELECT * FROM movie WHERE id=:id")
    fun findById(id: Long): Flow<MovieLocal?>

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieLocal>>

}