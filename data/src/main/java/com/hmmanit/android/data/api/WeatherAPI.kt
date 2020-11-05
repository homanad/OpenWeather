package com.hmmanit.android.data.api

import com.hmmanit.android.data.data.WeatherResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<WeatherResponseData>
}