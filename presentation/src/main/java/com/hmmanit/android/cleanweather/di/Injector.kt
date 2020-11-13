package com.hmmanit.android.cleanweather.di

import android.content.Context
import com.hmmanit.android.data.api.WeatherApiBuilder
import com.hmmanit.android.data.datasource.LocalWeatherDataSourceImpl
import com.hmmanit.android.data.datasource.RemoteWeatherDataSourceImpl
import com.hmmanit.android.data.db.WeatherDatabase
import com.hmmanit.android.data.repository.WeatherRepositoryImpl
import com.hmmanit.android.domain.repository.WeatherRepository
import com.hmmanit.android.domain.usecase.GetWeatherUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Injector {

    @Synchronized
    fun getGetWeatherUseCase(context: Context): GetWeatherUseCase {
        return GetWeatherUseCase(
            getWeatherRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    private fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepositoryImpl(
            getLocalWeatherDataSource(context),
            remoteWeatherDataSource
        )
    }

    private fun getLocalWeatherDataSource(context: Context): LocalWeatherDataSourceImpl {
        return LocalWeatherDataSourceImpl(WeatherDatabase.getInstance(context))
    }

    private val remoteWeatherDataSource by lazy {
        RemoteWeatherDataSourceImpl(WeatherApiBuilder.getWeatherService())
    }
}