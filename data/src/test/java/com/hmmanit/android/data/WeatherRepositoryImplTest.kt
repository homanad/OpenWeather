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
    private val weatherResponseEntity = WeatherResponseEntity()
    private val weatherResponseEntity2 = WeatherResponseEntity()

    @Before
    fun init() {
        weatherResponseEntity.weather.add(
            WeatherEntity(123, "test", "test", "test")
        )
        weatherResponseEntity2.weather.add(
            WeatherEntity(1234, "", "", "")
        )
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


//import android.content.Context
//import com.hmman.cleanweather.di.WeatherInjector
//import com.hmman.data.db.WeatherDatabase
//import com.hmman.data.mapper.DataToEntityMapper
//import com.hmman.data.mapper.EntityToDataMapper
//import com.hmman.data.repository.LocalWeatherImpl
//import com.hmman.domain.entity.WeatherEntity
//import com.hmman.domain.entity.WeatherResponseEntity
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.MockitoAnnotations
//
//
//class WeatherRepositoryImplTest {
//
//    private val weatherResponseEntity = WeatherResponseEntity()
//
//    @Mock
//    private val mockApplicationContext: Context? = null
//
//    @Mock
//    private val weatherDatabase: WeatherDatabase? = null
//
////    private lateinit var weatherDatabase: WeatherDatabase
////    private lateinit var weatherDAO: WeatherDAO
//
//    @Before
//    fun init() {
//        MockitoAnnotations.initMocks(this)
//        `when`(mockApplicationContext!!.applicationContext).thenReturn(mockApplicationContext)
//
//        weatherResponseEntity.weather.add(
//            WeatherEntity(123, "test", "test", "test")
//        )
//
////        weatherDatabase = mockk()
////        weatherDAO = mockk<WeatherDAO>()
////        every { weatherDatabase.getWeatherDao() } returns weatherDAO
////
//    }
//
//    //    @Test
////    fun `should return local weather`() {
////
////        val localWeatherImpl = LocalWeatherImpl(
////            weatherDatabase,
////            DataToEntityMapper(),
////            EntityToDataMapper()
////        )
////        val remoteWeatherImpl = RemoteWeatherImpl(WeatherApiBuilder.getWebService())
////
////        val repo = WeatherRepositoryImpl(localWeatherImpl, remoteWeatherImpl)
////
////
////
//////        remoteWeatherImpl.getWeather("Hanoi")
////        localWeatherImpl.getWeather()
////
////        val myDao = mock<WeatherDAO>() {
////            on { getWeather() }.thenReturn(
////                EntityToDataMapper().mapWeatherResponseEntityToData(
////                    weatherResponseEntity
////                ).weather
////            )
////        }
////        every { weatherDatabase.getWeatherDao() } returns myDao
////
////
////        repo.getLocalWeather().test().await().assertValue { it == weatherResponseEntity }
////    }
//
//    @Test
//    fun `should return local weather`() {
//        val localUsecase = LocalWeatherImpl(weatherDatabase!!, DataToEntityMapper(), EntityToDataMapper())
//        val repository = WeatherInjector.getWeatherRepository(mockApplicationContext!!)
//
//        localUsecase.saveLocalWeather(weatherResponseEntity)
//
////        repository.
//        localUsecase.getWeather().test().await().assertValue {
//            it == weatherResponseEntity
//        }
//    }
//
//
//    @Test
//    fun `should return`() {
////        val server = MockWebServer()
////        server.start()
//
//
//    }
//
//}
