package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.common.Mapper
import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.domain.entity.WeatherResponseEntity

class WeatherResponseEntityMapper : Mapper<WeatherResponseEntity, WeatherResponseData> {
    override fun map(from: WeatherResponseEntity): WeatherResponseData {
        return WeatherResponseData(from.weather.map {
            WeatherEntityMapper().map(it)
        }.toMutableList())
    }
}