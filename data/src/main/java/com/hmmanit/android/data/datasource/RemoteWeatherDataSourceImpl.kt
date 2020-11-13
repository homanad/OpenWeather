package com.hmmanit.android.data.datasource

import com.hmmanit.android.data.api.WeatherAPI
import com.hmmanit.android.data.common.DataConstants
import com.hmmanit.android.data.mapper.WeatherResponseDataMapper
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

class RemoteWeatherDataSourceImpl(private val weatherAPI: WeatherAPI) : RemoteWeatherDataSource {

    override fun getWeather(cityName: String): Single<WeatherResponseEntity> {
        return weatherAPI.getWeather(cityName, DataConstants.WEATHER_UNIT, DataConstants.API_KEY)
            .map { WeatherResponseDataMapper().map(it) }
    }
}