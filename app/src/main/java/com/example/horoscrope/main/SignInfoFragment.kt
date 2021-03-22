package com.example.horoscrope.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.horoscrope.R
import com.example.horoscrope.main.signs.WeekdayViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SignInfoFragment : Fragment(R.layout.fragment_sign_info) {


    var textViewSign: TextView? = null
    var signImage: ImageView? = null
    var signsDate: TextView? = null
    var signId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_info, container, false)

        textViewSign = view?.findViewById(R.id.textView_sign)
        signImage = view?.findViewById(R.id.signImage)
        signsDate = view?.findViewById(R.id.signsDate)
        signId =
            activity!!.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE).getInt("Sign", 0)
        val nextSignButton: ImageButton = view.findViewById(R.id.nextSignButton)
        val prevSignButton: ImageButton = view.findViewById(R.id.prevSignButton)

        changeSign(signId)

        nextSignButton.setOnClickListener {
            changeSign(signId + 1)
        }
        prevSignButton.setOnClickListener {
            changeSign(signId - 1)
        }
        val adapter = WeekdayViewPagerAdapter()
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = adapter;

        val tabLayout: TabLayout = view.findViewById(R.id.weekdayTab)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.tabs_titles)[position]
        }.attach()
        return view
    }

    fun changeSign(id: Int) {
        if (id > 11) signId = 0
        else if (id < 0) signId = 11
        else signId = id

        signImage?.setImageResource(
            resources.obtainTypedArray(R.array.signsImages).getResourceId(signId, 0)
        )
        textViewSign?.text = resources.getStringArray(R.array.signs)[signId]
        signsDate?.text = resources.getStringArray(R.array.signs_date)[signId]
    }
}