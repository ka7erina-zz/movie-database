package com.example.moviedb.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toastMessage(throwable: Throwable) {
    Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}