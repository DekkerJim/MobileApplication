package com.example.comicproject.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comicproject.R
import com.example.comicproject.model.api.Data
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicAdapter(private val comics: List<Data>, private val onClick: (Data) -> Unit) : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {
    private lateinit var context: Context

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int = comics.size

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind( comics[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(comics[adapterPosition]) }
        }
        fun bind(data: Data) {
            //Log.i("LogTitle", data.getPosterUrl().toString())

            itemView.ivTitle.text = data.attributes.slug
            Glide.with(context).load(data.getPosterUrl()).into(itemView.ivPoster)
            //Glide.with(context).load(data.attributes.posterImage).into(itemView.ivPoster)
        }
    }
}