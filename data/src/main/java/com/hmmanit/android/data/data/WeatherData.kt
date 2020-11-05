package com.hmmanit.android.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
    @PrimaryKey
    var id: Int = 0,
    var main: String = "",
    var description: String = "",
    var icon: String = ""
)