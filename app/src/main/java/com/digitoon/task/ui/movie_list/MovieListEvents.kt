package com.digitoon.task.ui.movie_list

import android.view.View

sealed class MovieListEvents {
    class NavigateToMovieDetails(val view : View, val movieId : Long): MovieListEvents()
    class SendMessage(val message: String?) : MovieListEvents()
    data object InitView : MovieListEvents()
}