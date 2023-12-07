package com.digitoon.task.data.network

import com.digitoon.task.data.network.dto.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("")
    suspend fun getMovieList(
        @Query("apikey") apikey: String = "3e974fca",
        @Query("s") s: String = "batman"
    ): Response<MovieListResponse>

    @GET("")
    suspend fun getMovieDetails(
        @Query("apikey") apikey: String = "3e974fca",
        @Query("i") movieId: String
    ): Response<MovieListResponse>
}