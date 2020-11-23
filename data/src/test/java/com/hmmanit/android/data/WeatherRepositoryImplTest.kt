package com.hmmanit.android.data

import com.hmmanit.android.data.datasource.LocalWeatherDataSource
import com.hmmanit.android.data.datasource.RemoteWeatherDataSource
import com.hmmanit.android.data.repository.WeatherRepositoryImpl
import com.hmmanit.android.domain.entity.WeatherEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test


class WeatherRepositoryImplTest {
    private val weatherResponseEntity =
        WeatherResponseEntity(listOf(WeatherEntity(123, "test", "test", "test")))
    private val weatherResponseEntity2 =
        WeatherResponseEntity(listOf(WeatherEntity(1234, "", "", "")))

    @Before
    fun init() {
    }

    private val remoteDataStore = mock<RemoteWeatherDataSource> {
        on { getWeather("Hanoi") }.thenReturn(Single.just(weatherResponseEntity))
    }

    private val localDataStore = mock<LocalWeatherDataSource> {
        on { getWeather() }.thenReturn(Single.just(weatherResponseEntity))
    }

    @Test
    fun shouldGetRemoteWeather() {

        val repo = WeatherRepositoryImpl(localDataStore, remoteDataStore)

        repo.getWeather("Hanoi").test().await().assertValue {
            it == weatherResponseEntity
        }
    }

    @Test
    fun shouldGetLocalWeather() {

        val repo = WeatherRepositoryImpl(localDataStore, remoteDataStore)

        repo.getWeather("Hanoi").test().await().assertValue {
            it == weatherResponseEntity
        }
    }

}