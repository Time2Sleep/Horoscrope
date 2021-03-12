package com.example.horoscrope

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    var state: Int = 1;
    val nameFragment = NameFragment()
    val genderFragment = GenderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, nameFragment)
            commit();
        }
    }

     fun next(view: View) {
        state++;
        when (state) {
            1 -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout, nameFragment)
                commit();
            }
            2 -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout, genderFragment)
                commit();
            }
        }
    }
}