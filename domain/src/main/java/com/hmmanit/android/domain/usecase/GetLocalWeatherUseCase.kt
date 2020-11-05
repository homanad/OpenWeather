package com.hmmanit.android.domain.usecase

import com.hmmanit.android.domain.common.SingleUseCase
import com.hmmanit.android.domain.entity.WeatherResponseEntity
import com.hmmanit.android.domain.repository.WeatherRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GetLocalWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    uiThread: Scheduler,
    executorThread: Scheduler
) : SingleUseCase<WeatherResponseEntity>(uiThread, executorThread) {

    override fun create(): Single<WeatherResponseEntity> {
        return weatherRepository.getLocalWeather().timeout(2, TimeUnit.SECONDS)
    }
}