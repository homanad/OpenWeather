package com.hmmanit.android.domain.repository

import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

interface WeatherRepository {
    fun getLocalWeather(): Single<WeatherResponseEntity>
    fun getRemoteWeather(cityName: String): Single<WeatherResponseEntity>
}