package com.example.horoscrope.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.horoscrope.R

class LoveFragment : Fragment(R.layout.fragment_love) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_love, container, false)
        val btn: Button = view.findViewById(R.id.nextButtonLove)

        btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(
                    R.id.mainFrameLayout,
                    CompatibilityFragment()
                )
                commit()
            }
        }

        return view
    }
}