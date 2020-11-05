package com.hmmanit.android.data.datasource

import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

interface RemoteWeatherDataSource {
    fun getWeather(cityName: String): Single<WeatherResponseEntity>
}