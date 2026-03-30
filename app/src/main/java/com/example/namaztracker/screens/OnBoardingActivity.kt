package com.example.namaztracker.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.namaztracker.NamazListActivity
import com.example.namaztracker.R
import com.example.namaztracker.databinding.ActivityOnBoardingBinding
import com.example.namaztracker.hideStatusBar
import com.example.namaztracker.screens.onboard.ViewPagerAdapter
import com.example.namaztracker.screens.onboard.fragments.ScreenOneFragment
import com.example.namaztracker.screens.onboard.fragments.ScreenThreeFragment
import com.example.namaztracker.screens.onboard.fragments.ScreenTwoFragment

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var fragmentList: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        fragmentList = arrayListOf(
            ScreenOneFragment(),
            ScreenTwoFragment(),
            ScreenThreeFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        setupButtons()
        setupPageChangeListener()
    }

    private fun setupButtons() {
        binding.btNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem

            if (currentItem < fragmentList.size - 1) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                finishOnboarding()
            }
        }

        binding.btSkip.setOnClickListener {
            finishOnboarding()
        }
    }

    private fun setupPageChangeListener() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtonsVisibility(position)
            }
        })

        updateButtonsVisibility(0)
    }

    private fun updateButtonsVisibility(position: Int) {
        when (position) {
            fragmentList.size - 1 -> {
                binding.btNext.text = "Get Started"
                binding.btSkip.visibility = View.GONE
            }
            else -> {
                binding.btNext.text = "Next"
                binding.btSkip.visibility = View.VISIBLE
            }
        }
    }

    private fun finishOnboarding() {
        // Save preference
        val sharedPref = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("onboarding_completed", true).apply()

        // Navigate to main screen
        val intent = Intent(this, NamazListActivity::class.java)
        startActivity(intent)
        finish()
    }
}