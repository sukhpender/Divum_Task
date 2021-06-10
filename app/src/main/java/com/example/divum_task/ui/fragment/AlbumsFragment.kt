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
import com.example.divum_task.adapters.AlbumsAdapter
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.AlbumsResponse
import com.example.divum_task.ui.AlbumDetailsActivity
import com.example.divum_task.ui.presenter.PostPresenter
import kotlinx.android.synthetic.main.fragment_albums.*

class AlbumsFragment : Fragment(), CommonPresenterInterface {

    var postPresenter: PostPresenter? = null
    var albumList = ArrayList<AlbumsResponse.AlbumsResponseItem>()
    var albumsAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        albums_progress_bar.visibility = View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        postPresenter = PostPresenter(this)
        postPresenter?.callAlbumsApi()
    }

    override fun onSuccess(response: Any?) {
        if (response is AlbumsResponse) {
            albumList.addAll(response)

            albums_progress_bar.visibility = View.INVISIBLE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

            albumsAdapter = AlbumsAdapter(requireContext(), albumList) { item, pos ->
                val intent = Intent(context, AlbumDetailsActivity::class.java)
                intent.putExtra("AlbumDetailId", item.id.toString())
                startActivity(intent)
            }
            rv_albums?.adapter = albumsAdapter
            albumsAdapter?.notifyDataSetChanged()
        }
    }

    override fun apiFailed(title: String?) {
        albums_progress_bar.visibility = View.INVISIBLE
        Toast.makeText(context, title, Toast.LENGTH_LONG).show()
    }
}