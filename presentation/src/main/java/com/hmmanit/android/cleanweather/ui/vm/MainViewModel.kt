package com.hmmanit.android.cleanweather.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmmanit.android.cleanweather.common.BaseViewModel
import com.hmmanit.android.cleanweather.mapper.WeatherResponseMapper
import com.hmmanit.android.cleanweather.model.WeatherResponse
import com.hmmanit.android.domain.usecase.GetLocalWeatherUseCase
import com.hmmanit.android.domain.usecase.GetRemoteWeatherUseCase

class MainViewModel(
    private val getRemoteWeatherUseCase: GetRemoteWeatherUseCase,
    private val getLocalWeatherUseCase: GetLocalWeatherUseCase
) : BaseViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse> = _weatherResponse

    fun getWeatherFromRemote(cityName: String) {
        disposables.add(
            getRemoteWeatherUseCase(cityName)
                .doOnSubscribe {
                    showLoading(true)
                }
                .doFinally {
                    showLoading(false)
                }
                .subscribe({
                    _weatherResponse.value = WeatherResponseMapper().map(it)
                }, {
                    showMessage(it.message!!)
                })
        )
    }

    fun getWeatherFromLocal() {
        disposables.add(
            getLocalWeatherUseCase()
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({
                    _weatherResponse.value = WeatherResponseMapper().map(it)
                }, {
                    showMessage(it.message!!)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}