package com.sschmitz.kmm_weather

import com.sschmitz.kmm.data.repository.WeatherRepository
import com.sschmitz.kmm_weather.domain.model.FullForecast

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    suspend fun getFullForecast(): FullForecast {
        return WeatherRepository().getFullForecast()
    }
}
