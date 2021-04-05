package com.example.moviedb.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.models.CollectionDetails
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.list_item_details.view.*

class DetailsAdapter(
    private val collections: ArrayList<CollectionDetails>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_details, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        collections[position].let { (holder as ViewHolder).bind(it) }
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(collection: CollectionDetails) {
            Glide.with(context)
                .load(BuildConfig.IMAGE_URL + collection.poster_path)
                .placeholder(R.color.gray)
                .into(containerView.collection_image)
            containerView.collection_title.text = collection.name
        }
    }

    override fun getItemCount(): Int {
        return collections.size
    }
}