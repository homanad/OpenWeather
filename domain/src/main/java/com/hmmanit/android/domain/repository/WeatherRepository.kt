package com.hmmanit.android.domain.repository

import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(cityName: String): Single<WeatherResponseEntity>
}