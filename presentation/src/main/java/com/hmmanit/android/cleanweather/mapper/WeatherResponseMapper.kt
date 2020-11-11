package com.hmmanit.android.cleanweather.mapper

import com.hmmanit.android.cleanweather.model.WeatherResponse
import com.hmmanit.android.domain.entity.WeatherResponseEntity

class WeatherResponseMapper : Mapper<WeatherResponseEntity, WeatherResponse> {
    override fun map(from: WeatherResponseEntity): WeatherResponse {
        return WeatherResponse(from.weather.map {
            WeatherMapper().map(it)
        })
    }
}