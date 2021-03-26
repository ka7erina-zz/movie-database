package com.example.moviedb.ui.main

import com.example.moviedb.api.MoviesAPI
import com.example.moviedb.models.Movies
import com.example.moviedb.utils.unwrapResponse
import com.example.moviedb.utils.with
import io.reactivex.Single

class MainViewModel {
    private val moviesAPI: MoviesAPI by lazy {
        MoviesAPI.create()
    }

    fun getMovies(): Single<Movies> {
        return moviesAPI.getCurrentMovies().with().unwrapResponse()
    }
}