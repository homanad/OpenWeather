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
) : SingleUseCaseWithParam<WeatherResponseEntity, String>(uiThread, executorThread) {

    override fun create(param: String): Single<WeatherResponseEntity> {
        return weatherRepository.getWeather(param)
            .timeout(5, TimeUnit.SECONDS)
    }
}