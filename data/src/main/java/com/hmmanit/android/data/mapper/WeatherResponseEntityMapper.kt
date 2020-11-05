package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.domain.entity.WeatherResponseEntity

class WeatherResponseEntityMapper : Mapper<WeatherResponseEntity, WeatherResponseData> {
    override fun map(from: WeatherResponseEntity): WeatherResponseData {
        val weatherList = from.weather.map {
            WeatherEntityMapper().map(it)
        }
        return WeatherResponseData(weatherList)
    }
}