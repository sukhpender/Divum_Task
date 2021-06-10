package com.example.divum_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divum_task.R
import com.example.divum_task.model.AlbumsResponse
import kotlinx.android.synthetic.main.albums_item_layout.view.*

class AlbumsAdapter(
    private val context: Context,
    private val albumList: ArrayList<AlbumsResponse.AlbumsResponseItem>,
    var itemClick: (AlbumsResponse.AlbumsResponseItem, Int) -> Unit
) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.albums_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = albumList.get(position)
        holder.itemView.txt_title_albums.text = items.title
        holder.itemView.setOnClickListener {
            itemClick(items, position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}