package com.example.divum_task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.divum_task.R
import com.example.divum_task.ui.fragment.AlbumsFragment
import com.example.divum_task.ui.fragment.PostsFragment
import com.example.divum_task.ui.fragment.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        loadFragment(PostsFragment())
        init()
    }

    private fun init() {


        botm_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.post -> {
                    //supportActionBar?.title = "Posts"
                    loadFragment(PostsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.users -> {
                    // supportActionBar?.title = "Users"
                    loadFragment(UsersFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.albums -> {
                    // supportActionBar?.title = "Albums"
                    loadFragment(AlbumsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_lay_main, fragment)
        transaction.addToBackStack("null")
        transaction.commit()
    }
}