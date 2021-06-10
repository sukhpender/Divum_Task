package com.example.divum_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.divum_task.R
import com.example.divum_task.model.AlbumDetailResponse
import com.example.divum_task.model.PostDetailsResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album_details.view.*
import kotlinx.android.synthetic.main.item_post_details.view.*

class PostDetailsAdapter(
    private val context: Context,
    private val postDetailsList: ArrayList<PostDetailsResponse.PostDetailsResponseItem>
) : RecyclerView.Adapter<PostDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_post_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postDetailsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = postDetailsList.get(position)
        holder.itemView.txt_post_detail_name.text = items.name
        holder.itemView.txt_post_detail_email.text = items.email
        holder.itemView.txt_post_detail_description.text = items.body
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}