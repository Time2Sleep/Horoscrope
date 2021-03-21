package com.example.horoscrope.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.horoscrope.R


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPrefs = activity!!.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        var signId = sharedPrefs.getInt("Sign",0)

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        var signProfileImage: ImageView = view.findViewById(R.id.signProfileImage)
        var signName: TextView = view.findViewById(R.id.signName)
        var signDates: TextView = view.findViewById(R.id.signDates)
        var profileGender: TextView = view.findViewById(R.id.profileGender)
        var profileBirth: TextView = view.findViewById(R.id.profileBirth)
        var profileName: TextView = view.findViewById(R.id.profileName)

        signProfileImage.setImageResource(resources.obtainTypedArray(R.array.signsImages).getResourceId(signId, 0))
        signDates.text = resources.getStringArray(R.array.signs_date)[signId]
        signName.text = resources.getStringArray(R.array.signs)[signId]
        profileGender.text = if(sharedPrefs.getBoolean("isMale", true)) "Мужской" else "Женский"
        profileName.text = sharedPrefs.getString("Name", "null")

        var birthDay = sharedPrefs.getString("Birthday", "0.0.0")
        var day = birthDay?.substringBefore(".")
        var month = (birthDay?.substringAfter("."))?.substringBefore(".")?.toInt()
        var year = (birthDay?.substringAfter("."))?.substringAfter(".")
        profileBirth.text = "$day      "+months[month!!-1]+"      $year"

        return view
    }
}