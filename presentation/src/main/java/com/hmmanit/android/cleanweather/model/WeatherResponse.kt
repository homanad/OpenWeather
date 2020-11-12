package com.hmmanit.android.cleanweather.model

data class WeatherResponse(
    var weather: MutableList<Weather> = mutableListOf()
)