package com.example.moviedb.api

import com.example.moviedb.BuildConfig
import com.example.moviedb.models.CollectionDetails
import com.example.moviedb.models.MovieDetails
import com.example.moviedb.models.Movies
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    companion object {
        fun create(): MoviesAPI {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BuildConfig.API_URL)
                .build()

            return retrofit.create(MoviesAPI::class.java)
        }
    }

    @GET("movie/now_playing")
    fun getCurrentMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<Movies>

    @GET("movie")
    fun getMovieDetails(
        @Query("movie_id") movieId: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<MovieDetails>

    @GET("movie")
    fun getCollections(
        @Query("collection_id") movieId: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<CollectionDetails>
}