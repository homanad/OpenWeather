package com.hmmanit.android.data.datasource

import com.hmmanit.android.data.api.WeatherAPI
import com.hmmanit.android.data.mapper.WeatherResponseDataMapper
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

class RemoteWeatherDataSourceImpl(private val weatherAPI: WeatherAPI) : RemoteWeatherDataSource {

    override fun getWeather(cityName: String): Single<WeatherResponseEntity> {
        return weatherAPI.getWeather(cityName, "metric", "02d12f3623d749e35f096a218d430d3a")
            .map { WeatherResponseDataMapper().map(it) }
    }
}