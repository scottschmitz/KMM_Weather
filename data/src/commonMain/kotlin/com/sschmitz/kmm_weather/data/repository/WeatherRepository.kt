package com.sschmitz.kmm.data.repository

import com.sschmitz.kmm.data.datasource.WeatherApi
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.domain.model.FullForecast

class WeatherRepository : WeatherContract {

  private val api = WeatherApi()


  @Throws(Exception::class)
  override suspend fun getFullForecast(): FullForecast {
    return api.fullForecastForPoint().toFullForecast()
  }
}
