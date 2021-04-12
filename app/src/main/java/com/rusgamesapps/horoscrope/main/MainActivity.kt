package com.rusgamesapps.horoscrope.main

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.rusgamesapps.horoscrope.R

class MainActivity : AppCompatActivity() {

    val fragments = arrayOf(SignInfoFragment(), LoveFragment(), MoonFragment(), ProfileFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navButtons: Array<ImageButton> = arrayOf(
            findViewById(R.id.navbar_main_btn),
            findViewById(R.id.navbar_love_btn),
            findViewById(R.id.navbar_moon_btn),
            findViewById(R.id.navbar_profile_btn))

        for(id in navButtons.indices){
            navButtons[id].setOnClickListener {
                changeFragment(id)
            }
        }
        AndroidNetworking.initialize(this);
        changeFragment(0)
    }

    fun changeFragment(id:Int){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.fragments_in,R.anim.fragments_out)
            replace(R.id.mainFrameLayout,fragments[id])
            addToBackStack(null)
            commit();
        }
    }
}