package com.digitoon.task.data.repository

import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.data.network.dto.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import com.digitoon.task.data.network.Result

interface MovieRepository {

    suspend fun fetchMovieList() : Flow<List<MovieLocal>>
//    fun getMovie(bookId: Long): Flow<MovieLocal?>
//
//    fun getMoviesFromLocal() : Flow<List<MovieLocal>>
//
//    suspend fun addAll(bookList : List<MovieLocal>)
//
//    fun getMoviesFromRemote(): Flow<Result<MovieListResponse>>
//
//    suspend fun updateMovie (book: MovieLocal)
}