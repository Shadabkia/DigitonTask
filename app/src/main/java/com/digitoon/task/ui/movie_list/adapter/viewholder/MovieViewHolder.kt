package com.digitoon.task.ui.movie_list.adapter.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.digitoon.task.R
import com.digitoon.task.data.db.entity.MovieLocal
import com.digitoon.task.databinding.ItemMovieBinding
import com.digitoon.task.ui.movie_list.adapter.MovieListener

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val listener: MovieListener,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: MovieListener,
            context: Context
        ): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieBinding.inflate(inflater, parent, false)
            return MovieViewHolder(binding, listener, context)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(movie: MovieLocal) {
        binding.apply {

            // Set id for each movie item card for animation transition
            ViewCompat.setTransitionName(
                clContainer,
                context.getString(R.string.movie_card_transition_name, movie.id.toString())
            )

            textViewTitle.text = movie.title

            textViewIMDB.text = movie.imdbID
            textViewType.text = movie.type
            textViewYear.text = movie.year

            Glide
                .with(context)
                .load(movie.poster)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .placeholder(R.drawable.movie_icon)
                .into(imageViewPoster)

            root.setOnClickListener {
                listener.onMovieClicked(binding.root, movie.id)
            }
        }
    }
}
