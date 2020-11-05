package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.domain.entity.WeatherResponseEntity

class WeatherResponseDataMapper : Mapper<WeatherResponseData, WeatherResponseEntity> {
    override fun map(from: WeatherResponseData): WeatherResponseEntity {
        val weatherList = from.weather.map {
            WeatherDataMapper().map(it)
        }
        return WeatherResponseEntity(weatherList)
    }
}