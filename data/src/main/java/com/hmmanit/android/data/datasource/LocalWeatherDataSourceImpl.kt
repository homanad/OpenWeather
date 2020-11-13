package com.hmmanit.android.data.datasource

import android.util.Log
import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.data.db.WeatherDatabase
import com.hmmanit.android.data.mapper.WeatherEntityMapper
import com.hmmanit.android.data.mapper.WeatherResponseDataMapper
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

class LocalWeatherDataSourceImpl(
    database: WeatherDatabase
) : LocalWeatherDataSource {

    private val dao = database.getWeatherDao()

    override fun getWeather(): Single<WeatherResponseEntity> {
        return Single.just(
            WeatherResponseDataMapper().map(
                WeatherResponseData(
                    dao.getWeather().toMutableList()
                )
            )
        )
    }

    override fun saveWeather(weatherResponseEntity: WeatherResponseEntity) {
        dao.clearAllData()
        weatherResponseEntity.weather.map {
            dao.insertWeather(WeatherEntityMapper().map(it))
        }
    }
}