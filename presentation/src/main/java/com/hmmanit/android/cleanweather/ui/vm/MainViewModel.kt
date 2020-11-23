package com.hmmanit.android.cleanweather.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmmanit.android.cleanweather.common.BaseViewModel
import com.hmmanit.android.cleanweather.mapper.toWeatherResponse
import com.hmmanit.android.cleanweather.model.WeatherResponse
import com.hmmanit.android.domain.usecase.GetWeatherUseCase

class MainViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : BaseViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse> = _weatherResponse

    fun getWeather(cityName: String) {
        disposables.add(
            getWeatherUseCase(cityName)
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    _weatherResponse.value = it.toWeatherResponse()
                }, {
                    showMessage(it.message!!)
                })
        )
    }
}