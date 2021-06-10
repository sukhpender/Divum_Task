package com.example.divum_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divum_task.R
import com.example.divum_task.model.PostsResponse
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostsAdapter(
    private val context: Context,
    private val postList: ArrayList<PostsResponse.PostsResponseItem>,
    var itemClick: (PostsResponse.PostsResponseItem, Int) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.post_item_layout, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = postList.get(position)
        holder.itemView.txt_title_posts.text = items.title
        holder.itemView.txt_post_description.text = items.body
        holder.itemView.setOnClickListener {
            itemClick(items, position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}