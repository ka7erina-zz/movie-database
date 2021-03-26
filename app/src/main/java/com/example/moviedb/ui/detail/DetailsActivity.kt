package com.example.moviedb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.R
import com.example.moviedb.models.CollectionDetails
import com.example.moviedb.utils.INTENT_EXTRA_MOVIE_ID
import com.example.moviedb.utils.RecyclerViewDecoration
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val vm = DetailsViewModel()
    private lateinit var detailsAdapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val collectionId = intent.extras?.getString(INTENT_EXTRA_MOVIE_ID)
        if (collectionId != null) {
            getCollections(collectionId)
        } else {
            rv_list_collections.visibility = View.INVISIBLE
            collections_title.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("CheckResult")
    private fun getCollections(collectionId: String) {
        vm.getCollections(collectionId)
            .subscribe({
                createCollections(it)
            },
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                })
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