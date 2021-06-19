package com.application.moviecatalogue.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.application.moviecatalogue.R

class SplashActivity : AppCompatActivity() {
    private val splashTime: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler(mainLooper).postDelayed({
            val splashIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(splashIntent)
            finish()
        }, splashTime)
    }
}