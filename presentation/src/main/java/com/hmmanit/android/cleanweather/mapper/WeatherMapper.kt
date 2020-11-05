package com.hmmanit.android.cleanweather.mapper

import com.hmmanit.android.cleanweather.model.Weather
import com.hmmanit.android.domain.entity.WeatherEntity

class WeatherMapper : Mapper<WeatherEntity, Weather> {
    override fun map(from: WeatherEntity): Weather {
        return Weather(from.id, from.main, from.description, from.icon)
    }
}