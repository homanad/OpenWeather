package com.hmmanit.android.data.mapper

import com.hmmanit.android.data.common.Mapper
import com.hmmanit.android.data.data.WeatherData
import com.hmmanit.android.domain.entity.WeatherEntity

class WeatherDataMapper : Mapper<WeatherData, WeatherEntity> {
    override fun map(from: WeatherData): WeatherEntity =
        WeatherEntity(from.id, from.main, from.description, from.icon)
}