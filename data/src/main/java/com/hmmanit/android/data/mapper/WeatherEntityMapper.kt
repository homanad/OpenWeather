package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.common.Mapper
import com.hmmanit.android.data.data.WeatherData
import com.hmmanit.android.domain.entity.WeatherEntity

class WeatherEntityMapper : Mapper<WeatherEntity, WeatherData> {
    override fun map(from: WeatherEntity): WeatherData =
        WeatherData(from.id, from.main, from.description, from.icon)
}