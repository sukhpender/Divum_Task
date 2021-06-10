package com.example.divum_task.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.divum_task.R
import com.example.divum_task.adapters.PostDetailsAdapter
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.PostDetailsResponse
import com.example.divum_task.ui.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity(), CommonPresenterInterface {

    private var postId: String? = null
    var detailsPresenter: DetailsPresenter? = null
    var postDetailsList = ArrayList<PostDetailsResponse.PostDetailsResponseItem>()
    var postDetailsAdapter: PostDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        init()

        btn_back_post.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        postId = intent.getStringExtra("PostDetailId").toString()
        txt_post_id_details.text = "Post Id : $postId"
        detailsPresenter = DetailsPresenter(this)
        detailsPresenter?.callPostDetailsApi(postId)
    }

    override fun onSuccess(response: Any?) {
        if (response is PostDetailsResponse) {
            postDetailsList.addAll(response)

            post_details_progress_bar.visibility = View.GONE
            postDetailsAdapter = PostDetailsAdapter(this, postDetailsList)
            rv_post_details?.adapter = postDetailsAdapter
            postDetailsAdapter?.notifyDataSetChanged()
        }
    }

    override fun apiFailed(title: String?) {
        Toast.makeText(this, title, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}