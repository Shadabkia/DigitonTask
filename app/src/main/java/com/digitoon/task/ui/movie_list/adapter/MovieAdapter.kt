package com.digitoon.task.ui.movie_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.ui.movie_list.adapter.viewholder.MovieViewHolder

class MovieAdapter(
    private val listener : MovieListener
) : ListAdapter<MovieLocal, MovieViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<MovieLocal>() {

        override fun areItemsTheSame(oldItem: MovieLocal, newItem: MovieLocal) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieLocal,
            newItem: MovieLocal
        ) =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.create(parent, listener, parent.context)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position))


}