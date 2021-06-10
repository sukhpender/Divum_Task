package com.example.divum_task.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.divum_task.R
import com.example.divum_task.adapters.AlbumDetailsAdapter
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.AlbumDetailResponse
import com.example.divum_task.ui.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_album_details.*
import kotlinx.android.synthetic.main.activity_post_details.*

class AlbumDetailsActivity : AppCompatActivity(), CommonPresenterInterface {

    private var albumId: String? = null
    var detailsPresenter: DetailsPresenter? = null
    var albumDetailsList = ArrayList<AlbumDetailResponse.AlbumDetailResponseItem>()
    var albumDetailsAdapter: AlbumDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        btn_back_album.setOnClickListener {
            finish()
        }
        init()
    }

    private fun init() {
        albumId = intent.getStringExtra("AlbumDetailId").toString()
        txt_album_id_details.text = "Album Id : $albumId"
        album_details_progress_bar.visibility = View.VISIBLE
        detailsPresenter = DetailsPresenter(this)
        detailsPresenter?.callAlbumsDetailsApi(albumId)
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onSuccess(response: Any?) {
        if (response is AlbumDetailResponse) {
            albumDetailsList.addAll(response)
            album_details_progress_bar.visibility = View.INVISIBLE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            albumDetailsAdapter = AlbumDetailsAdapter(this, albumDetailsList)
            rv_album_details?.adapter = albumDetailsAdapter
            albumDetailsAdapter?.notifyDataSetChanged()
        }
    }

    override fun apiFailed(title: String?) {
        album_details_progress_bar.visibility = View.INVISIBLE
        Toast.makeText(this, title, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}