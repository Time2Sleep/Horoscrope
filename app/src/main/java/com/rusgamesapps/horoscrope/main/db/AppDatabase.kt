package com.rusgamesapps.horoscrope.main.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Horoscope::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun horoscopeDao(): HoroscopeDao

}