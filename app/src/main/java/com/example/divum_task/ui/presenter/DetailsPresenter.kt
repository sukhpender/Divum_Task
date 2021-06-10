package com.example.divum_task.ui.presenter

import com.example.divum_task.common.ApiInterface
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.AlbumDetailResponse
import com.example.divum_task.model.PostDetailsResponse
import com.example.divum_task.ui.ApiClient
import retrofit2.Callback
import retrofit2.Response

class DetailsPresenter(private val commonPresenterInterface: CommonPresenterInterface?) {

    var apiInterface: ApiInterface? = null

    fun callAlbumsDetailsApi(albumId: String?) {
        apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.fetchAlbumDetails("https://jsonplaceholder.typicode.com/albums/$albumId/photos")
            ?.enqueue(object : Callback<AlbumDetailResponse?> {

                override fun onFailure(call: retrofit2.Call<AlbumDetailResponse?>, t: Throwable) {
                    commonPresenterInterface?.apiFailed(t.localizedMessage)
                }

                override fun onResponse(
                    call: retrofit2.Call<AlbumDetailResponse?>,
                    response: Response<AlbumDetailResponse?>
                ) {

                    if (response.isSuccessful) {
                        val respo = response.body()
                        commonPresenterInterface?.onSuccess(respo)
                    }
                }
            })
    }

    fun callPostDetailsApi(postId: String?) {
        apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        apiInterface?.fetchPostDetails("https://jsonplaceholder.typicode.com/albums/$postId/comments")
            ?.enqueue(object : Callback<PostDetailsResponse?> {

                override fun onFailure(call: retrofit2.Call<PostDetailsResponse?>, t: Throwable) {
                    commonPresenterInterface?.apiFailed(t.localizedMessage)
                }

                override fun onResponse(
                    call: retrofit2.Call<PostDetailsResponse?>,
                    response: Response<PostDetailsResponse?>
                ) {

                    if (response.isSuccessful) {
                        val respo = response.body()
                        commonPresenterInterface?.onSuccess(respo)
                    }
                }
            })
    }
}