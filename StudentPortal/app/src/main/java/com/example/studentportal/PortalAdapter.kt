package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portal_item.view.*

class PortalAdapter (private val portals: List<Portal>) :
RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal: Portal) {
            itemView.tvTitle.text = portal.title
            itemView.tvUrl.text = portal.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portal_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: PortalAdapter.ViewHolder, position: Int) {
        holder.bind(portals[position])
    }
}