package com.example.divum_task.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.divum_task.R
import com.example.divum_task.adapters.PostsAdapter
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.PostsResponse
import com.example.divum_task.ui.PostDetailsActivity
import com.example.divum_task.ui.presenter.PostPresenter
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment(), CommonPresenterInterface {

    var postPresenter: PostPresenter? = null
    var postList = ArrayList<PostsResponse.PostsResponseItem>()
    var postsAdapter: PostsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        posts_progress_bar.visibility = View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        postPresenter = PostPresenter(this)
        postPresenter?.callPostsApi()
    }

    override fun onSuccess(response: Any?) {
        if (response is PostsResponse) {
            postList.addAll(response)

            posts_progress_bar.visibility = View.INVISIBLE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            postsAdapter = PostsAdapter(requireContext(), postList) { item, pos ->
                val intent = Intent(context, PostDetailsActivity::class.java)
                intent.putExtra("PostDetailId", item.id.toString())
                startActivity(intent)
            }
            rv_posts?.adapter = postsAdapter
            postsAdapter?.notifyDataSetChanged()
        }
    }

    override fun apiFailed(title: String?) {
        posts_progress_bar.visibility = View.INVISIBLE
        Toast.makeText(context, title, Toast.LENGTH_LONG).show()
    }
}