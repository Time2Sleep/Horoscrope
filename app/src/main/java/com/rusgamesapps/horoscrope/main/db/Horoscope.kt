package com.rusgamesapps.horoscrope.main.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Horoscope(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "sign") val sign: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "timePeriod") val timePeriod: String,
    @ColumnInfo(name = "description") val description: String
)