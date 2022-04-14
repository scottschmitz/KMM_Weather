package com.sschmitz.kmm.domain.contract

import com.sschmitz.kmm_weather.domain.model.FullForecast

interface WeatherContract {
  @Throws(Exception::class)
  suspend fun getFullForecast(): FullForecast
}
