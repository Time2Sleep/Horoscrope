package com.rusgamesapps.horoscrope.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.rusgamesapps.horoscrope.R
import com.rusgamesapps.horoscrope.main.db.AppDatabase
import com.rusgamesapps.horoscrope.main.db.Horoscope
import com.rusgamesapps.horoscrope.main.dto.GetHoroscopeDto
import com.rusgamesapps.horoscrope.main.dto.Period
import com.rusgamesapps.horoscrope.main.dto.Sign
import org.json.JSONObject
import java.util.*

class MoonFragment : Fragment(R.layout.fragment_moon) {
    val months = arrayOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_moon, container, false)
        val timeMillisToAsk = System.currentTimeMillis()
        val period = Period.LUNAR;
        val dateString = period.fromDate(Date(timeMillisToAsk))
        val moonDate: TextView = view.findViewById(R.id.moonDate)
        val monthNum = dateString.substringAfter('-').substringBefore('-').toInt()
        val humanDate = dateString.substringAfter('-').substringAfter('-').toInt()
            .toString() + " " + months[monthNum - 1]
        moonDate.text = humanDate

        val db = Room.databaseBuilder(
            view.context,
            AppDatabase::class.java, "horoscope-db"
        ).allowMainThreadQueries().build()
        val signId =
            context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)?.getInt("Sign", 0)
        val sign = Sign.values()[signId ?: 0]
        val request = GetHoroscopeDto(period.name, dateString, sign.name)
        val horoscope =
            db.horoscopeDao().findByDateAndSignAndPeriod(dateString, sign.name, period.name)
        if (horoscope != null) {
            view?.findViewById<TextView>(R.id.lunarHoroscopeDescription)?.text =
                horoscope.description
        } else {
            AndroidNetworking.post("https://env-2828191.mircloud.ru/api/horoscope")
                .setPriority(Priority.LOW)
                .addApplicationJsonBody(request)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val description = response?.getString("description") ?: ""
                        view?.findViewById<TextView>(R.id.lunarHoroscopeDescription)?.text =
                            description
                        val newHoroscope =
                            Horoscope(0, sign.name, dateString, period.name, description)
                        db.horoscopeDao().insert(newHoroscope)
                    }

                    override fun onError(anError: ANError?) {
                        view?.findViewById<TextView>(R.id.lunarHoroscopeDescription)?.text =
                            "Не получилось загрузить гороскоп на указанные даты"
                    }
                })
        }

        return view
    }
}