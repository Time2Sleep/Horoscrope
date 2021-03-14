package com.example.horoscrope

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*


class BirthFragment : Fragment(R.layout.fragment_birth) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_birth, container, false)
        var birthdayTextView: TextView? = view?.findViewById(R.id.textViewBirthday)
        var pickDate: ImageButton? = view?.findViewById(R.id.pickBirthday)
        var nextButton: Button? = view?.findViewById(R.id.nextButton)
        nextButton?.isEnabled = false
        var birthDay: String = "0.0.0"
        val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря")
        pickDate?.setOnClickListener {
            var calendar: Calendar = Calendar.getInstance()
            var year: Int = calendar.get(Calendar.YEAR)
            var month: Int = calendar.get(Calendar.MONTH)
            var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                birthdayTextView?.text = ("$dayOfMonth      "+months[monthOfYear]+"      $year")
                birthdayTextView?.setTextColor(Color.parseColor("#242F4B"))
                birthDay = "$dayOfMonth."+(monthOfYear+1).toString()+".$year"
            }, year, month, day).show()
            nextButton?.isEnabled = true
        }

        nextButton?.setOnClickListener {

            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()?.putString("Birthday", birthDay)?.apply()
            activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.edit()?.putString("Sign", getSign(birthDay))?.apply()
            Log.d("sign", getSign(birthDay))
            val intent = Intent(activity, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.overridePendingTransition(R.anim.activities_transition_in,R.anim.activities_transition_out)
        }
        return view
    }

    fun getSign(birthday: String): String {
        val day: Int = birthday.substringBefore(".").toInt()
        val month: Int = (birthday.substringAfter(".")).substringBefore(".").toInt()
        if((day>=21&&month==3)||(day<=19&&month==4)) return "Овен"
        if((day>=20&&month==4)||(day<=20&&month==5)) return "Телец"
        if((day>=21&&month==5)||(day<=21&&month==6)) return "Близнецы"
        if((day>=22&&month==6)||(day<=22&&month==7)) return "Рак"
        if((day>=23&&month==7)||(day<=22&&month==8)) return "Лев"
        if((day>=23&&month==8)||(day<=22&&month==9)) return "Дева"
        if((day>=23&&month==9)||(day<=23&&month==10)) return "Весы"
        if((day>=24&&month==10)||(day<=22&&month==11)) return "Скорпион"
        if((day>=23&&month==11)||(day<=21&&month==12)) return "Стрелец"
        if((day>=22&&month==12)||(day<=20&&month==1)) return "Козерог"
        if((day>=21&&month==1)||(day<=18&&month==2)) return "Водолей"
        if((day>=19&&month==2)||(day<=20&&month==3)) return "Рыбы"
        return ""
    }
}