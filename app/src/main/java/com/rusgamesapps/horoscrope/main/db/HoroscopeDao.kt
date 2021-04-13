package com.rusgamesapps.horoscrope.main.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

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

    @Query("DELETE FROM horoscope")
    fun deleteAll()
}