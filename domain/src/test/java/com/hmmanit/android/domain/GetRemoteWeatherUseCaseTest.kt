package com.hmmanit.android.domain

import com.hmmanit.android.domain.entity.WeatherEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.repository.WeatherRepository
import com.hmmanit.android.domain.usecase.GetWeatherUseCase
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class GetWeatherUseCaseTest {

    private val weatherResponseEntity =
        WeatherResponseEntity(listOf(WeatherEntity(123, "test", "test", "test")))

    @Before
    fun init() {
    }

    @Test
    fun `should create GetRemoteWeatherUseCase`() {
        val weatherRepository = mock<WeatherRepository>()
        Assert.assertNotNull(
            GetWeatherUseCase(
                weatherRepository,
                Schedulers.io(),
                Schedulers.io()
            )
        )
    }

    @Test
    fun `should return weather`() {
        val weatherRepository = mock<WeatherRepository> {
            on { getWeather("Hanoi") }.thenReturn(Single.just(weatherResponseEntity))
        }

        val useCase =
            GetWeatherUseCase(weatherRepository, Schedulers.io(), Schedulers.io())

        useCase("Hanoi").test().await().assertValue {
            it == weatherResponseEntity
        }
    }

    @Test
    fun `should throw TimeOutException case 1`() {
        val weatherRepository = mock<WeatherRepository> {
            on { getWeather("Hanoi") }.thenReturn(Single.error(TimeoutException()))
        }

        val useCase =
            GetWeatherUseCase(weatherRepository, Schedulers.io(), Schedulers.io())

        useCase("Hanoi")
            .test()
            .await()
            .assertError {
                it is TimeoutException
            }
    }

    @Test
    fun `should throw TimeOutException case `() {
        val weatherRepository = mock<WeatherRepository> {
            on { getWeather("Hanoi") }.thenReturn(
                Single.just(weatherResponseEntity).delay(11, TimeUnit.SECONDS)
            )
        }

        val useCase =
            GetWeatherUseCase(weatherRepository, Schedulers.io(), Schedulers.io())

        useCase("Hanoi").test().await().assertError {
            it is TimeoutException
        }
    }
}