package com.example.cameraxapp

import PhotoFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cameraxapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager(binding.viewPager)

        // Add OnPageChangeCallback to ViewPager2
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateStatusBarAndIndicator(position)
            }
        })
        // Register the page change callback to update the dot indicators
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotIndicator(position)
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2  // We have two fragments

            override fun createFragment(position: Int): Fragment {
                return if (position == 0) PhotoFragment() else VideoFragment()
            }
        }
        viewPager.adapter = adapter
    }

    private fun updateStatusBarAndIndicator(position: Int) {
        val message = when (position) {
            0 -> "Photo Capture"
            else -> "Video Capture"

        }
        // Update the status bar, for example:
        supportActionBar?.title = message
    }
    private fun updateDotIndicator(position: Int) {
        binding.dot1.setImageResource(if (position == 0) R.drawable.dot_selected else R.drawable.dot_unselected)
        binding.dot2.setImageResource(if (position == 1) R.drawable.dot_selected else R.drawable.dot_unselected)
    }
}

