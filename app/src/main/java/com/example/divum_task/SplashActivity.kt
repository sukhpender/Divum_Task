package com.example.divum_task

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.divum_task.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            openA()
        }, 2000)
    }

    private fun openA() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}