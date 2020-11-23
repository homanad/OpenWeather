package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.data.WeatherData
import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.domain.entity.WeatherEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity

fun WeatherData.toWeatherEntity() = WeatherEntity(this.id, this.main, this.description, this.icon)

fun WeatherEntity.toWeatherData() = WeatherData(this.id, this.main, this.description, this.icon)

fun WeatherResponseData.toWeatherResponseEntity() =
    WeatherResponseEntity(this.weather.map { item -> item.toWeatherEntity() }.toMutableList())