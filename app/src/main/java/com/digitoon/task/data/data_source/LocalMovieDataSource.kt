package com.digitoon.task.data.data_source

import com.digitoon.task.data.db.DigitoonDataBase
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.data.network.Result
import com.digitoon.task.data.network.dto.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.List

class LocalMovieDataSource @Inject constructor(
    private val db: DigitoonDataBase,
) {

    suspend fun updateMovieList(res: Result<MovieListResponse>) {
        Timber.tag("personal_info").d("updateUser")
        when (res) {
            is Result.Error -> {
                Timber.tag("personal_info").d(" updateUser failed : ${res.message}")
            }

            is Result.Loading -> {

            }

            is Result.Success -> {
                Timber.tag("personal_info").d(" updateUser Success")
                res.data?.search?.map {search ->
                    MovieLocal(
                        title = search.title,
                        imdbID = search.imdbID,
                        type = search.type,
                        year = search.year,
                        poster = search.poster
                    )
                }?.let { db.movieDao().addAll(it) }
//                dataStoreManager.updateUserLogin(0L)
            }
        }
    }

    fun fetchMovieListLocal(): Flow<List<MovieLocal>> =
        db.movieDao().getAll()

}