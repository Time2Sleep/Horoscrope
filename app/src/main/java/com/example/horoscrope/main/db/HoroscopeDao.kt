package com.example.horoscrope.main.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.horoscrope.main.dto.Period
import java.util.*

@Dao
interface HoroscopeDao {

    @Query("SELECT * FROM horoscope h where h.date = :date and h.sign= :sign and h.timePeriod = :timePeriod")
    fun findByDateAndSignAndPeriod(
        date: String,
        sign: String,
        timePeriod: String
    ): Horoscope?

    @Insert
    fun insert(horoscope: Horoscope)
}