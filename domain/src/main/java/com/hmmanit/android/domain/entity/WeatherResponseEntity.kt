package com.hmmanit.android.domain.entity

data class WeatherResponseEntity(
    var weather: MutableList<WeatherEntity> = mutableListOf()
)