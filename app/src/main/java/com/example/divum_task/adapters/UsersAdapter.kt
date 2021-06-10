package com.example.divum_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.divum_task.R
import com.example.divum_task.model.UserResponse
import kotlinx.android.synthetic.main.users_layout.view.*

class UsersAdapter(
    private val context: Context,
    private val userList: ArrayList<UserResponse.UserResponseItem>,
    val itemClick: (UserResponse.UserResponseItem) -> Unit
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.users_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = userList.get(position)
        holder.itemView.txt_username.text = items.username
        holder.itemView.txt_user_email.text = items.email
        holder.itemView.txt_user_website.text = items.website
        holder.itemView.txt_user_phone.text = items.phone
        holder.itemView.txt_user_c_name.text = items.company.name
        holder.itemView.txt_user_catchPhrase.text = items.company.catchPhrase
        holder.itemView.txt_user_Comp_bs.text = items.company.bs
        holder.itemView.txt_user_street.text = items.address.street
        holder.itemView.txt_user_suite.text = items.address.suite
        holder.itemView.txt_user_city.text = items.address.city
        holder.itemView.txt_user_zipcode.text = items.address.zipcode
        holder.itemView.setOnClickListener {
            itemClick(items)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}