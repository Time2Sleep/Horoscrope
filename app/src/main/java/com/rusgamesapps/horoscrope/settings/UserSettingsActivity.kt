package com.rusgamesapps.horoscrope.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rusgamesapps.horoscrope.R

class UserSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.frameLayout,
                NameFragment()
            )
            addToBackStack(null)
            commit()
        }
    }
}