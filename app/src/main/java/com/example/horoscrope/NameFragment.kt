package com.example.horoscrope

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class NameFragment : Fragment(R.layout.fragment_name) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nextButton: Button? = view?.findViewById(R.id.nextButton)
        nextButton?.setOnClickListener {

        }
    }
}