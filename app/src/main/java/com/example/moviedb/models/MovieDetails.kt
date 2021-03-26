package com.example.moviedb.models

data class MovieDetails(
    val genres: ArrayList<Genres>,
    val belongs_to_collection: String,
    val title: String,
    val overview: String,
    val vote_average: String,
    val poster_path: String
)