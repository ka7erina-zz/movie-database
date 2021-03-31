package com.example.moviedb.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.models.Results
import com.example.moviedb.ui.detail.DetailsActivity
import com.example.moviedb.utils.INTENT_EXTRA_MOVIE_ID
import com.example.moviedb.utils.RecyclerViewDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMovies()

    }

    @SuppressLint("CheckResult")
    private fun getMovies() {
        vm.getMovies()
            .subscribe({
               createList(it.results)},
                this::toastMessage
            )
    }

    private fun toastMessage(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun createList(movieResults: ArrayList<Results>) {
        mainAdapter =
            MainAdapter(movieResults, this@MainActivity, object : MovieClickListener {
                override fun onMovieClick(movieId: String) {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
                        putExtra(INTENT_EXTRA_MOVIE_ID, movieId)
                    }
                    startActivity(intent)
                }
            })
        rv_list.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            addItemDecoration(RecyclerViewDecoration())
        }
    }
}