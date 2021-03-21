package com.example.horoscrope.main.signs

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
import com.androidnetworking.interfaces.StringRequestListener
import com.example.horoscrope.R
import com.example.horoscrope.settings.BirthFragment
import org.json.JSONArray

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
        AndroidNetworking.get("https://guarded-escarpment-96153.herokuapp.com/horoscope")
            .setPriority(Priority.LOW)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                        response.toString()
                }

                override fun onError(anError: ANError?) {
                    holder.itemView.findViewById<TextView>(R.id.weekdayPagerText).text =
                        anError.toString()
                }

            })


    }
}