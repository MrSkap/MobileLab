package com.example.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class OnBoardActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_long_distance_relationship,
                    title = "Learn anytime \n and anywhere",
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_staying_home,
                    title = "Find a course \n for you",
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.cool_kids_high_tech,
                    title = "Improve your skills",
                ),
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.viewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<Button>(R.id.onboardingButton).setOnClickListener{
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigateToSignUpActivity()
            }
        }
        findViewById<TextView>(R.id.skipText).setOnClickListener{
            navigateToSignUpActivity()
        }
    }
    private fun navigateToSignUpActivity() {
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
        finish()
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.linearLayoutPagination)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12,0,12,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_off
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_on
                    )
                )
                if (position == childCount - 1) {
                    findViewById<Button>(R.id.onboardingButton).setText(R.string.start)
                } else {
                    findViewById<Button>(R.id.onboardingButton).setText(R.string.next)
                }
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_off
                    )
                )
            }
        }
    }
}
