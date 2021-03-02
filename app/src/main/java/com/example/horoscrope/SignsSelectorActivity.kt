package com.example.horoscrope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class SignsSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signs_selector)
    }

    fun selectSign(view: View) {
        view as ImageButton
        val selectedSign = view.tag;
        selectedSign as String
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("sign", selectedSign)
        }
        startActivity(intent)
    }
}