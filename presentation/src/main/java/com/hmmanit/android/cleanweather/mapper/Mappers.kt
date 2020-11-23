package com.hmmanit.android.cleanweather.mapper

import com.hmmanit.android.cleanweather.model.Weather
import com.hmmanit.android.cleanweather.model.WeatherResponse
import com.hmmanit.android.domain.entity.WeatherEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity

fun WeatherEntity.toWeather() = Weather(this.id, this.main, this.description, this.icon)

fun WeatherResponseEntity.toWeatherResponse() =
    WeatherResponse(this.weather.map { item -> item.toWeather() }.toMutableList())