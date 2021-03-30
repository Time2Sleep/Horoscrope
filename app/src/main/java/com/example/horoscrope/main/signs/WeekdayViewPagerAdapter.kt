package com.example.horoscrope.main.signs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.example.horoscrope.R
import com.example.horoscrope.main.dto.GetHoroscopeDto
import com.example.horoscrope.main.dto.Period
import com.example.horoscrope.main.dto.Sign
import com.example.horoscrope.settings.BirthFragment
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.util.*
import kotlin.coroutines.coroutineContext

class WeekdayViewPagerAdapter :
    RecyclerView.Adapter<WeekdayViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weekday_view_pager, parent, false);
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val period = Period.fromInt(position)
        var timeMillisToAsk = System.currentTimeMillis()
        if (position == 1) {
            timeMillisToAsk += 86400000 //+1 day in millis
        }
        val dateString = period.fromDate(Date(timeMillisToAsk))
        println(dateString)
        val signId =
            holder.itemView.context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                .getInt("Sign", 0)
        val sign = Sign.values()[signId]
        val request = GetHoroscopeDto(period.name, dateString, sign.name)
        AndroidNetworking.post("https://guarded-escarpment-96153.herokuapp.com/api/horoscope")
            .setPriority(Priority.LOW)
            .addApplicationJsonBody(request)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                        response?.getString("description")
                }

                override fun onError(anError: ANError?) {
                    holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                        "Не получилось загрузить гороскоп на указанные даты"
                }

            })
    }
}