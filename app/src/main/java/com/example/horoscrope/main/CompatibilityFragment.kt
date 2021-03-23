package com.example.horoscrope.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horoscrope.R

class CompatibilityFragment : Fragment(R.layout.fragment_compatibility) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_compatibility, container, false)


        return view
    }
}