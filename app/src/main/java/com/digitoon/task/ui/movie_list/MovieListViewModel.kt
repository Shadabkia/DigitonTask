package com.digitoon.task.ui.movie_list

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val movieListEventsChannel = Channel<MovieListEvents>()
    val movieListEvents = movieListEventsChannel.receiveAsFlow()

    fun fragmentCreated() = viewModelScope.launch {
        movieListEventsChannel.send(MovieListEvents.InitView)
    }

    init {
        getMovieList()
        getMovieFromDatabase()
    }

    var isLoading = MutableStateFlow(true)
        private set


    // Get Movies from remote and update database
    fun getMovieList() = viewModelScope.launch {
        movieRepository.fetchMovieList().collectLatest {
            movieList.value = it
        }
    }

    var movieList = MutableStateFlow<List<MovieLocal>>(arrayListOf())
        private set

    // Get Movies from database and show in UI
    private fun getMovieFromDatabase() = viewModelScope.launch {
        Timber.d("getMovieFromDatabase")
        movieRepository.fetchMovieList().collectLatest {
            movieList.value = it
        }
    }

    fun onMovieClicked(view: View, movieId: Long) = viewModelScope.launch {
        movieListEventsChannel.send(MovieListEvents.NavigateToMovieDetails(view, movieId))
    }


}