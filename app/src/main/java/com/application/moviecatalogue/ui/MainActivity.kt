package com.application.moviecatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.moviecatalogue.R
import com.application.moviecatalogue.adapter.SectionsPagerAdapter
import com.application.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)?.setIcon(R.drawable.ic_baseline_local_movies_24)
        binding.tabs.getTabAt(1)?.setIcon(R.drawable.ic_baseline_live_tv_24)
    }
}