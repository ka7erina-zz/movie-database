package com.example.moviedb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.models.CollectionDetails
import com.example.moviedb.models.MovieDetails
import com.example.moviedb.utils.*
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val vm = DetailsViewModel()
    private lateinit var detailsAdapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val movieId = intent.extras?.getInt(INTENT_EXTRA_MOVIE_ID)
        if (movieId != null) {
            getMovieDetails(movieId)
        } else {
            rv_list_collections.hide()
            collections_title.hide()
        }
    }

    @SuppressLint("CheckResult")
    private fun getCollections(collectionId: Int) {
        vm.getCollections(collectionId)
            .subscribe({
                createCollections(it)
            },
                {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })
    }

    @SuppressLint("CheckResult")
    private fun getMovieDetails(movieId: Int) {
        vm.getMovieDetails(movieId)
            .subscribe(
                { movie ->
                    progress_bar.hide()
                    showMovieDetails(movie)
                    if (movie.belongs_to_collection != null) {
                        getCollections(movie.belongs_to_collection.id)
                    } else {
                        collections_title.visibility = View.GONE
                        rv_list_collections.visibility = View.GONE
                    }
                },
                this::toastMessage
            )
    }

    private fun showMovieDetails(movie: MovieDetails) {
        movie_title.show()
        movie_image.show()
        movie_overview.show()
        movie_title.text = movie.title
        movie_overview.text = movie.overview
        Glide.with(this@DetailsActivity)
            .load(BuildConfig.IMAGE_URL + movie.poster_path)
            .placeholder(R.color.gray)
            .into(movie_image)
    }

    private fun createCollections(collections: CollectionDetails) {
        detailsAdapter = DetailsAdapter(arrayListOf(collections), this@DetailsActivity)
        rv_list_collections.apply {
            adapter = detailsAdapter
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            addItemDecoration(RecyclerViewDecoration())
        }
    }
}