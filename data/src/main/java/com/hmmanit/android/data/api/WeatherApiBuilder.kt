package com.hmmanit.android.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiBuilder {
    companion object {
        private val apiInterface: WeatherAPI? = null
        private const val BASE_URL = "https://api.openweathermap.org"

        fun getWeatherService(): WeatherAPI {
            if (apiInterface != null) {
                return apiInterface
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(WeatherAPI::class.java)
        }
    }
}