package com.example.divum_task.ui.presenter

import android.util.Log
import com.example.divum_task.common.ApiInterface
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.AlbumsResponse
import com.example.divum_task.model.PostsResponse
import com.example.divum_task.model.UserResponse
import com.example.divum_task.ui.ApiClient
import retrofit2.Callback
import retrofit2.Response

class PostPresenter(private val commonPresenterInterface: CommonPresenterInterface?) {

    var apiInterface: ApiInterface? = null

    fun callPostsApi() {
        apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.fetchPosts()?.enqueue(object : Callback<PostsResponse?> {

            override fun onFailure(call: retrofit2.Call<PostsResponse?>, t: Throwable) {
                Log.e("posts", t.localizedMessage)
                commonPresenterInterface?.apiFailed(t.localizedMessage)
            }

            override fun onResponse(
                call: retrofit2.Call<PostsResponse?>,
                response: Response<PostsResponse?>
            ) {

                if (response.isSuccessful) {
                    val respo = response.body()
                    commonPresenterInterface?.onSuccess(respo)
                }
                Log.e("post", response.body().toString())
            }
        })
    }

    fun callUsersApi() {
        apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.fetchUsers()?.enqueue(object : Callback<UserResponse?> {

            override fun onFailure(call: retrofit2.Call<UserResponse?>, t: Throwable) {
                commonPresenterInterface?.apiFailed(t.localizedMessage)
            }

            override fun onResponse(
                call: retrofit2.Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {

                if (response.isSuccessful) {
                    val respo = response.body()
                    commonPresenterInterface?.onSuccess(respo)
                }
            }
        })
    }

    fun callAlbumsApi() {
        apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.fetchAlbums()?.enqueue(object : Callback<AlbumsResponse?> {

            override fun onFailure(call: retrofit2.Call<AlbumsResponse?>, t: Throwable) {
                commonPresenterInterface?.apiFailed(t.localizedMessage)
            }

            override fun onResponse(
                call: retrofit2.Call<AlbumsResponse?>,
                response: Response<AlbumsResponse?>
            ) {

                if (response.isSuccessful) {
                    val respo = response.body()
                    commonPresenterInterface?.onSuccess(respo)
                }
            }
        })
    }
}