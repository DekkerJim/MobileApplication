package com.example.comicproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comicproject.R
import com.example.comicproject.model.api.Data
import com.example.comicproject.model.entity.Comic
import kotlinx.android.synthetic.main.item_comic.view.*
import kotlinx.android.synthetic.main.item_saved.view.*

class ComicSaveAdapter(private val comics: List<Comic>) :
    RecyclerView.Adapter<ComicSaveAdapter.ViewHolder>() {

    private lateinit var context: Context

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_saved, parent, false)
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
        fun bind(comic: Comic) {
            itemView.tvNumber.text = (adapterPosition + 1).toString() + "."
            itemView.tvTitle.text = comic.slug
        }
    }
}