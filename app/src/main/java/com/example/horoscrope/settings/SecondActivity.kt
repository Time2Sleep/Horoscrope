package com.example.horoscrope.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscrope.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.frameLayout,
                NameFragment()
            )
            commit();
        }
    }
}