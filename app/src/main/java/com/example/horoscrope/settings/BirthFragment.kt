package com.example.horoscrope.settings

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.horoscrope.R
import com.example.horoscrope.main.MainActivity
import java.util.*


class BirthFragment : Fragment(R.layout.fragment_birth) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_birth, container, false)
        val birthdayTextView: TextView? = view?.findViewById(R.id.textViewBirthday)
        val pickDate: ImageButton? = view?.findViewById(R.id.pickBirthday)
        val nextButton: Button? = view?.findViewById(R.id.nextButton)
        nextButton?.isEnabled = false
        var birthDay = "0.0.0"
        val months = arrayOf(
            "Января",
            "Февраля",
            "Марта",
            "Апреля",
            "Мая",
            "Июня",
            "Июля",
            "Августа",
            "Сентября",
            "Октября",
            "Ноября",
            "Декабря"
        )
        pickDate?.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(
                requireActivity(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    birthdayTextView?.text =
                        ("$dayOfMonth      " + months[monthOfYear] + "      $year")
                    birthdayTextView?.setTextColor(Color.parseColor("#242F4B"))
                    birthDay = "$dayOfMonth." + (monthOfYear + 1).toString() + ".$year"
                },
                year,
                month,
                day
            ).show()
            nextButton?.isEnabled = true
        }

        nextButton?.setOnClickListener {
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()
                ?.putString("Birthday", birthDay)
                ?.apply()
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()
                ?.putInt("Sign", getSign(birthDay))
                ?.apply()
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()
                ?.putBoolean("profile_completed", true)
                ?.apply()
            val intent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.overridePendingTransition(
                R.anim.activities_transition_in,
                R.anim.activities_transition_out
            )
        }
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