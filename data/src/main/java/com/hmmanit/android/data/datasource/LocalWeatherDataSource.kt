package com.hmmanit.android.data.datasource

import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

interface LocalWeatherDataSource {
    fun getWeather(): Single<WeatherResponseEntity>
    fun saveWeather(weatherResponseEntity: WeatherResponseEntity)
}