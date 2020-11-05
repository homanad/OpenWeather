package com.hmmanit.android.data.repository

import com.hmmanit.android.data.datasource.LocalWeatherDataSource
import com.hmmanit.android.data.datasource.RemoteWeatherDataSource
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val localDataSource: LocalWeatherDataSource,
    private val remoteDataSource: RemoteWeatherDataSource
) : WeatherRepository {

    override fun getLocalWeather(): Single<WeatherResponseEntity> {
        return localDataSource.getWeather()
    }

    override fun getRemoteWeather(cityName: String): Single<WeatherResponseEntity> {
        return remoteDataSource.getWeather(cityName).doOnSuccess {
            localDataSource.saveWeather(it)
        }
    }
}