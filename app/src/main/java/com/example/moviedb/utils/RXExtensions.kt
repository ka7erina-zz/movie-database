package com.example.moviedb.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.with(): Single<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.unwrapResponse(): Single<T> = flatMap { response ->
    when (response) {
        null -> Single.error(Exception("Unknown error"))
        else -> Single.just(response)
    }
}