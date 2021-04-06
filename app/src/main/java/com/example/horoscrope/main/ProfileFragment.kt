package com.example.horoscrope.main

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.horoscrope.R
import java.util.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря")

    var birthDay = "0.0.0"
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
        var profileBirthBtn: ImageButton = view.findViewById(R.id.profileChangeBirthday)
        var profileName: EditText = view.findViewById(R.id.profileName)

        signProfileImage.setImageResource(resources.obtainTypedArray(R.array.signsImages).getResourceId(signId, 0))
        signDates.text = resources.getStringArray(R.array.signs_date)[signId]
        signName.text = resources.getStringArray(R.array.signs)[signId]
        var isMale = sharedPrefs.getBoolean("isMale", true)
        profileGender.text = if(isMale) "Мужской" else "Женский"
        profileName.setText(sharedPrefs.getString("Name", "null"))
        profileName.doAfterTextChanged {
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putString("Name", profileName.text.toString())
                ?.apply()
        }


        profileGender.setOnClickListener {
            isMale = !isMale
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putBoolean("isMale", isMale)
                ?.apply()
            profileGender.text = if(isMale) "Мужской" else "Женский"
        }
        profileBirthBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                requireActivity(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    profileBirth?.text =
                        ("$dayOfMonth      " + months[monthOfYear] + "      $year")
                    profileBirth?.setTextColor(Color.parseColor("#242F4B"))
                    birthDay = "$dayOfMonth." + (monthOfYear + 1).toString() + ".$year"
                    activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()
                        ?.putString("Birthday", birthDay)
                        ?.apply()
                    activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()
                        ?.putInt("Sign", getSign(birthDay))
                        ?.apply()
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(
                            R.id.mainFrameLayout,
                            ProfileFragment()
                        )
                        commit()
                    }
                },
                year,
                month,
                day
            ).show()


        }


        var birthDay = sharedPrefs.getString("Birthday", "1.1.2020")
        var day = birthDay?.substringBefore(".")
        var month = (birthDay?.substringAfter("."))?.substringBefore(".")?.toInt()
        var year = (birthDay?.substringAfter("."))?.substringAfter(".")
        profileBirth.text = "$day      "+months[month!!-1]+"      $year"

        return view
    }

    fun getSign(birthday: String): Int {
        val day: Int = birthday.substringBefore(".").toInt()
        val month: Int = (birthday.substringAfter(".")).substringBefore(".").toInt()
        if ((day >= 21 && month == 3) || (day <= 19 && month == 4)) return 0
        if ((day >= 20 && month == 4) || (day <= 20 && month == 5)) return 1
        if ((day >= 21 && month == 5) || (day <= 21 && month == 6)) return 2
        if ((day >= 22 && month == 6) || (day <= 22 && month == 7)) return 3
        if ((day >= 23 && month == 7) || (day <= 22 && month == 8)) return 4
        if ((day >= 23 && month == 8) || (day <= 22 && month == 9)) return 5
        if ((day >= 23 && month == 9) || (day <= 23 && month == 10)) return 6
        if ((day >= 24 && month == 10) || (day <= 22 && month == 11)) return 7
        if ((day >= 23 && month == 11) || (day <= 21 && month == 12)) return 8
        if ((day >= 22 && month == 12) || (day <= 20 && month == 1)) return 9
        if ((day >= 21 && month == 1) || (day <= 18 && month == 2)) return 10
        if ((day >= 19 && month == 2) || (day <= 20 && month == 3)) return 11
        return 0
    }
}