package com.hmmanit.android.data.datasource

import com.hmmanit.android.data.api.WeatherAPI
import com.hmmanit.android.data.mapper.WeatherResponseDataMapper
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

class RemoteWeatherDataSourceImpl(private val weatherAPI: WeatherAPI) : RemoteWeatherDataSource {

    override fun getWeather(cityName: String): Single<WeatherResponseEntity> {
        return weatherAPI.getWeather(cityName, "metric", "f5b947955b03f70187034971a725f6d3")
            .map { WeatherResponseDataMapper().map(it) }
    }
}