package com.example.horoscrope

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun toastMe(view: View){
        val myToast = Toast.makeText( this, "Hello, Azat!",Toast.LENGTH_SHORT)
        myToast.show()
    }
}