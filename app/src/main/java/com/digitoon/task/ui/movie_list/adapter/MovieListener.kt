package com.digitoon.task.ui.movie_list.adapter

import android.view.View

interface MovieListener {
    fun onMovieClicked(view : View, bookId : Long)
}
