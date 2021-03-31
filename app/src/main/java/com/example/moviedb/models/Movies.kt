package com.example.moviedb.models

data class Movies(
    val page: Int,
    val results: ArrayList<Results>,
    val id: Int
)