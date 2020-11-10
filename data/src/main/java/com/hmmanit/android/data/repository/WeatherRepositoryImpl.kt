package com.hmmanit.android.data.repository

import android.util.Log
import com.hmmanit.android.data.datasource.LocalWeatherDataSource
import com.hmmanit.android.data.datasource.RemoteWeatherDataSource
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val localDataSource: LocalWeatherDataSource,
    private val remoteDataSource: RemoteWeatherDataSource
) : WeatherRepository {
    override fun getWeather(isConnected: Boolean, cityName: String): Single<WeatherResponseEntity> {
        return if (isConnected) {
            Log.d("WeatherRepositoryImpl", "getRemoteWeather")
            remoteDataSource.getWeather(cityName).doOnSuccess {
                localDataSource.saveWeather(it)
            }
        } else {
            Log.d("WeatherRepositoryImpl", "getLocalWeather")
            localDataSource.getWeather()
        }
    }
}