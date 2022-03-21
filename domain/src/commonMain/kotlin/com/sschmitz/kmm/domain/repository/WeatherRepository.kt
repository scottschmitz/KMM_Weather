package com.sschmitz.kmm.domain.repository

import com.sschmitz.kmm.domain.datasource.WeatherApi
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm.domain.model.FullForecast

class WeatherRepository : WeatherContract {

  private val api = WeatherApi()

  override suspend fun getFullForecast(): FullForecast {
    return api.fullForecastForPoint().toFullForecast()
  }
}
