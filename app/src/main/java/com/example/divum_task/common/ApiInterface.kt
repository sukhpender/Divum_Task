package com.example.divum_task.common

import com.example.divum_task.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface ApiInterface {

    /* fetching posts */
    @GET("posts")
    fun fetchPosts(): Call<PostsResponse?>

    /* fetching users */
    @GET("users")
    fun fetchUsers(): Call<UserResponse?>

    /* fetching albums */
    @GET("albums")
    fun fetchAlbums(): Call<AlbumsResponse?>

    /* fetching album details */
    @GET
    fun fetchAlbumDetails(@Url url: String?): Call<AlbumDetailResponse?>

    /* fetching post details */
    @GET
    fun fetchPostDetails(@Url url: String?): Call<PostDetailsResponse?>

}