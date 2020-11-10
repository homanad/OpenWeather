package com.hmmanit.android.cleanweather.di

import android.content.Context
import com.hmmanit.android.data.api.WeatherApiBuilder
import com.hmmanit.android.data.datasource.LocalWeatherDataSourceImpl
import com.hmmanit.android.data.datasource.RemoteWeatherDataSourceImpl
import com.hmmanit.android.data.db.WeatherDatabase
import com.hmmanit.android.data.repository.WeatherRepositoryImpl
import com.hmmanit.android.domain.repository.WeatherRepository
import com.hmmanit.android.domain.usecase.GetLocalWeatherUseCase
import com.hmmanit.android.domain.usecase.GetRemoteWeatherUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Injector {

    @Synchronized
    fun getGetRemoteWeatherUseCase(context: Context): GetRemoteWeatherUseCase {
        return GetRemoteWeatherUseCase(
            getWeatherRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getGetLocalWeatherUseCase(context: Context): GetLocalWeatherUseCase {
        return GetLocalWeatherUseCase(
            getWeatherRepository(context),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
    }

    @Synchronized
    fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepositoryImpl(
            getLocalWeatherDataSource(context),
            getRemoteWeatherDataSource()
        )
    }

    @Synchronized
    fun getLocalWeatherDataSource(context: Context): LocalWeatherDataSourceImpl {
        return LocalWeatherDataSourceImpl(WeatherDatabase.getInstance(context))
    }

    @Synchronized
    fun getRemoteWeatherDataSource(): RemoteWeatherDataSourceImpl {
        return RemoteWeatherDataSourceImpl(WeatherApiBuilder.getWebService())
    }
}