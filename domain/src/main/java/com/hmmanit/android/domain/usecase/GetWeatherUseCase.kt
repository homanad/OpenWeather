package com.hmmanit.android.domain.usecase

import com.hmmanit.android.domain.common.SingleUseCaseWithParam
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.repository.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleUseCaseWithParam<WeatherResponseEntity, GetWeatherParams>(uiThread, executorThread) {

    override fun create(params: GetWeatherParams): Single<WeatherResponseEntity> {
        return weatherRepository.getWeather(params.isNetworkConnected, params.cityName)
            .timeout(2, TimeUnit.SECONDS)
    }
}

data class GetWeatherParams(
    var isNetworkConnected: Boolean = false,
    var cityName: String = ""
)