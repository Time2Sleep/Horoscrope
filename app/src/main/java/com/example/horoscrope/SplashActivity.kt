package com.example.horoscrope

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscrope.main.MainActivity
import com.example.horoscrope.settings.UserSettingsActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val circle: ImageView = findViewById(R.id.imageView_circle)
        circle.animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)

        val logo: ImageView = findViewById(R.id.imageView_logo)
        logo.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)

        val stars: ImageView = findViewById(R.id.imageView_stars)
        stars.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)
        val profileCompleted = getSharedPreferences(
            "sharedPrefs",
            Context.MODE_PRIVATE
        ).getBoolean("profile_completed", false)
        val nextActivity =
            if (!profileCompleted) UserSettingsActivity::class.java else MainActivity::class.java
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    this,
                    nextActivity
                )
            )
            overridePendingTransition(
                R.anim.activities_transition_in,
                R.anim.activities_transition_out
            )
            finish()
        }, SPLASH_TIME_OUT)
    }
}