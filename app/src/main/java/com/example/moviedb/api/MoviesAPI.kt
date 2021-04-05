package com.example.moviedb.api

import com.example.moviedb.BuildConfig
import com.example.moviedb.models.CollectionDetails
import com.example.moviedb.models.MovieDetails
import com.example.moviedb.models.Movies
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    companion object {
        var interceptor = HttpLoggingInterceptor()
        var client = OkHttpClient.Builder()
        fun create(): MoviesAPI {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(client.build())
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

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<MovieDetails>

    @GET("collection/{collection_id}")
    fun getCollections(
        @Path("collection_id") movieId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Single<CollectionDetails>
}