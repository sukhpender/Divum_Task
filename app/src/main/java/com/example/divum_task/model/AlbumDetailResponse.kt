package com.example.divum_task.model

class AlbumDetailResponse : ArrayList<AlbumDetailResponse.AlbumDetailResponseItem>() {

    data class AlbumDetailResponseItem(
        val albumId: Int,
        val id: Int,
        val thumbnailUrl: String,
        val title: String,
        val url: String
    )
}