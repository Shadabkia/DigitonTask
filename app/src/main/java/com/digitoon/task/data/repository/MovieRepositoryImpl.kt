package com.digitoon.task.data.repository

import com.digitoon.task.data.data_source.LocalMovieDataSource
import com.digitoon.task.data.data_source.RemoteMovieDataSource
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.data.network.SafeApiRequest
import com.digitoon.task.data.network.dto.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import com.digitoon.task.data.network.Result
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val rmd: RemoteMovieDataSource,
    private val lmd: LocalMovieDataSource
) : MovieRepository, SafeApiRequest() {

    override suspend fun fetchMovieList(): Flow<List<MovieLocal>> {
        try {
            rmd.fetchMovieList().collectLatest {
                lmd.updateMovieList(it)
            }
        } catch (e : Exception){
            Timber.tag("personal_info").d("cant get personal information ${e.message}")
        }
        return lmd.fetchMovieListLocal()

    }


//    override fun getMovie(movieId: Long): Flow<MovieLocal?> =
//        lmd.movieDao().findById(movieId)
//
//    override fun getMoviesFromLocal(): Flow<List<MovieLocal>> =
//        lmd.movieDao().getAll()
//
//    override suspend fun addAll(movieList: List<MovieLocal>) =
//        lmd.movieDao().addAll(movieList)
//
//    override suspend fun updateMovie(movie: MovieLocal)  =
//        lmd.movieDao().updateMovie(movie)
//
//    override fun getMoviesFromRemote(): Flow<Result<MovieListResponse>> =
//        apiRequest {  rmd.getMovieList() }

}