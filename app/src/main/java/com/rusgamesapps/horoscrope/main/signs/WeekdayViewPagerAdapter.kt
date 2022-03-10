package com.rusgamesapps.horoscrope.main.signs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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


class WeekdayViewPagerAdapter(private val signId: Int, private val db: AppDatabase) :
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
        val sign = Sign.values()[signId]
        val horoscope =
            db.horoscopeDao().findByDateAndSignAndPeriod(dateString, sign.name, period.name)
        if (horoscope != null) {
            holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                horoscope.description
        } else {
            val request = GetHoroscopeDto(period.name, dateString, sign.name)
            AndroidNetworking.post("https://env-2828191.mircloud.ru/api/horoscope")
                .setPriority(Priority.LOW)
                .addApplicationJsonBody(request)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val description = response?.getString("description")
                        holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                            description
                        val newHoroscope =
                            Horoscope(0, sign.name, dateString, period.name, description ?: "")
                        db.horoscopeDao().insert(newHoroscope)
                    }

                    override fun onError(anError: ANError?) {
                        holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                            "Не получилось загрузить гороскоп на указанные даты"
                    }
                })
        }
    }
}