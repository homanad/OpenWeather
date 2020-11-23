package com.hmmanit.android.data.datasource

import com.hmmanit.android.data.data.WeatherResponseData
import com.hmmanit.android.data.db.WeatherDatabase
import com.hmmanit.android.data.mapper.toWeatherData
import com.hmmanit.android.data.mapper.toWeatherResponseEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import io.reactivex.Single

class LocalWeatherDataSourceImpl(
    database: WeatherDatabase
) : LocalWeatherDataSource {

    private val dao = database.getWeatherDao()

    override fun getWeather(): Single<WeatherResponseEntity> {
        return Single.just(
            WeatherResponseData(dao.getWeather().toMutableList()).toWeatherResponseEntity()
        )
    }

    override fun saveWeather(weatherResponseEntity: WeatherResponseEntity) {
        dao.clearAllData()
        weatherResponseEntity.weather.map {
            dao.insertWeather(it.toWeatherData())
        }
    }
}