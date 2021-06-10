package com.example.divum_task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.divum_task.R
import com.example.divum_task.adapters.UsersAdapter
import com.example.divum_task.common.CommonPresenterInterface
import com.example.divum_task.model.UserResponse
import com.example.divum_task.ui.presenter.PostPresenter
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment(), CommonPresenterInterface {

    var postPresenter: PostPresenter? = null
    var userList = ArrayList<UserResponse.UserResponseItem>()
    var userAdapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        users_progress_bar.visibility = View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        postPresenter = PostPresenter(this)
        postPresenter?.callUsersApi()
    }

    override fun onSuccess(response: Any?) {
        if (response is UserResponse) {
            userList.addAll(response)

            users_progress_bar.visibility = View.INVISIBLE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            userAdapter = UsersAdapter(requireContext(), userList) {
                Toast.makeText(context, "Hiii  ${it.name}", Toast.LENGTH_LONG).show()
            }
            rv_users?.adapter = userAdapter
            userAdapter?.notifyDataSetChanged()
        }
    }

    override fun apiFailed(title: String?) {
        Toast.makeText(context, title, Toast.LENGTH_LONG).show()
        users_progress_bar.visibility = View.INVISIBLE

    }
}