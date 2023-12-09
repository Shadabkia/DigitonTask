package com.digitoon.task.data.data_source
import com.digitoon.task.data.network.RetrofitService
import com.digitoon.task.data.network.SafeApiRequest
import com.digitoon.task.data.network.dto.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import com.digitoon.task.data.network.Result
import timber.log.Timber
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(
    private val api: RetrofitService
) : SafeApiRequest() {

    fun fetchMovieList(): Flow<Result<MovieListResponse>> =
        apiRequest {
            api.getMovieList()
        }

}