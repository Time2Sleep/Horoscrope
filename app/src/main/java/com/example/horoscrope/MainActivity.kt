package com.example.horoscrope

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textViewSign: TextView = findViewById(R.id.textView_sign)
        textViewSign.text = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).getString("Sign","лох")
    }


}