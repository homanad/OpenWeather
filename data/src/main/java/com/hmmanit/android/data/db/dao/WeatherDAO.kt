package com.hmmanit.android.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hmmanit.android.data.data.WeatherData

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM WeatherData")
    fun getWeather(): List<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherData: WeatherData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherResponse(weatherResponse: List<WeatherData>)

    @Query("DELETE FROM WeatherData")
    fun clearAllData()
}