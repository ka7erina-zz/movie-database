package com.example.moviedb.ui.detail

import com.example.moviedb.api.MoviesAPI
import com.example.moviedb.models.CollectionDetails
import com.example.moviedb.models.MovieDetails
import com.example.moviedb.models.Movies
import com.example.moviedb.utils.unwrapResponse
import com.example.moviedb.utils.with
import io.reactivex.Single

class DetailsViewModel {
    private val moviesAPI: MoviesAPI by lazy {
        MoviesAPI.create()
    }

    fun getMovieDetails(movieId: String): Single<MovieDetails> {
        return moviesAPI.getMovieDetails(movieId).with().unwrapResponse()
    }

    fun getCollections(collectionId: String): Single<CollectionDetails> {
        return moviesAPI.getCollections(collectionId).with().unwrapResponse()
    }
}