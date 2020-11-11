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

    private fun remoteDataSourceWithSave(cityName: String) =
        remoteDataSource.getWeather(cityName).doOnSuccess { saveToLocal(it) }

    private fun saveToLocal(weatherResponseEntity: WeatherResponseEntity) =
        localDataSource.saveWeather(weatherResponseEntity)

    override fun getWeather(cityName: String): Single<WeatherResponseEntity> {
        return Single.concat(remoteDataSourceWithSave(cityName), localDataSource.getWeather())
            .firstOrError()
    }
}