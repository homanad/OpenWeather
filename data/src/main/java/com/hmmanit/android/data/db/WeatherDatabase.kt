package com.hmmanit.android.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hmmanit.android.data.data.WeatherData
import com.hmmanit.android.data.db.dao.WeatherDAO

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDAO

    companion object {
        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java,
                        WeatherDatabase.javaClass.name
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
}