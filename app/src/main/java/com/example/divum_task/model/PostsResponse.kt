package com.example.divum_task.model

class PostsResponse : ArrayList<PostsResponse.PostsResponseItem>(){
    data class PostsResponseItem(
        val body: String,
        val id: Int,
        val title: String,
        val userId: Int
    )
}