package com.example.divum_task.model

class AlbumsResponse : ArrayList<AlbumsResponse.AlbumsResponseItem>() {
    data class AlbumsResponseItem(
        val id: Int,
        val title: String,
        val userId: Int
    )
}