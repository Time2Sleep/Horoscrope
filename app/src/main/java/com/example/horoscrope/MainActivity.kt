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
        val myToast = Toast.makeText( this, "Hello, Toast!",Toast.LENGTH_SHORT)
        myToast.show()
    }

   /* fun count(view: View){
        var textView = findViewById<TextView>(R.id.count_num)
        var countString = Integer.parseInt(textView.text.toString())
        countString++
        textView.setText(countString.toString())
    }

    fun random(view: View){
        var textView = findViewById<TextView>(R.id.count_num)
        var countString = (0..100).random()
        textView.setText(countString.toString())
    }*/
}