package com.example.divum_task.model

class PostDetailsResponse : ArrayList<PostDetailsResponse.PostDetailsResponseItem>(){
    data class PostDetailsResponseItem(
        val body: String,
        val email: String,
        val id: Int,
        val name: String,
        val postId: Int
    )
}