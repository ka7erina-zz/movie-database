package com.example.moviedb.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.models.Movies
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_main.view.*

class MainAdapter(
    private val movies: ArrayList<Movies>,
    private val context: Context,
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_main, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        movies[position].let { (holder as ViewHolder).bind(it) }
        holder.itemView.setOnClickListener {
            movieClickListener.onMovieClick(movies[position].id)
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movies) {
            Glide.with(context).load(BuildConfig.API_URL + movie.results.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(containerView.movie_image)
            containerView.movie_title.text = movie.results.title
            containerView.movie_description.text = movie.results.overview
        }
    }
}