package com.example.divum_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divum_task.R
import com.example.divum_task.model.AlbumDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_details.view.*

class AlbumDetailsAdapter(
    private val context: Context,
    private val albumDetailsList: ArrayList<AlbumDetailResponse.AlbumDetailResponseItem>
) : RecyclerView.Adapter<AlbumDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_album_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumDetailsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = albumDetailsList.get(position)
        holder.itemView.txt_album_detail_album_id.text = items.id.toString()
        holder.itemView.txt_album_detail_title.text = items.title
        Picasso.get().load(items.url).placeholder(R.drawable.dlogo)
            .into(holder.itemView.img_album_detail)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}