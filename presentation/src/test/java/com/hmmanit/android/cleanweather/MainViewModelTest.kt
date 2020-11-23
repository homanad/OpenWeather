package com.hmmanit.android.cleanweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hmmanit.android.cleanweather.mapper.toWeatherResponse
import com.hmmanit.android.cleanweather.model.WeatherResponse
import com.hmmanit.android.cleanweather.ui.vm.MainViewModel
import com.hmmanit.android.domain.entity.WeatherEntity
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.usecase.GetWeatherUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    private val weatherResponseEntity =
        WeatherResponseEntity(listOf(WeatherEntity(123, "test", "test", "test")))
    private val weatherResponseEntity2 =
        WeatherResponseEntity(listOf(WeatherEntity(1234, "", "", "")))

    @Before
    fun init() {

    }

    @Test
    fun shouldReturnMainViewModel() {
        val mainViewModel = getMainViewModel()
        Assert.assertNotNull(mainViewModel)
    }

    @Test
    fun shouldReturnWeather() {
        val viewModel = getMainViewModel()
        viewModel.getWeather("Hanoi")

        val observer = mock<Observer<WeatherResponse>>()
        viewModel.weatherResponse.observeForever(observer)
        verify(observer).onChanged(
            weatherResponseEntity.toWeatherResponse()
        )
    }

    private val remoteWeatherUseCase = mock<GetWeatherUseCase> {
        on { invoke("Hanoi") }.thenReturn(Single.just(weatherResponseEntity))
    }

    private fun getMainViewModel(): MainViewModel {
        return MainViewModel(remoteWeatherUseCase)
    }
}