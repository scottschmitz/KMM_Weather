package com.sschmitz.kmm_weather.data.repository

import com.sschmitz.kmm_weather.data.datasource.WeatherApi
import com.sschmitz.kmm_weather.domain.contract.GridLocation
import com.sschmitz.kmm_weather.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.domain.model.FullForecast

class WeatherRepository : WeatherContract {

  private val api = WeatherApi()

  @Throws(Exception::class)
  override suspend fun getGridPositions(latitude: Double, longitude: Double): GridLocation {
    return api.getGridLocation(latitude, longitude).toGridLocation()
  }

  @Throws(Exception::class)
  override suspend fun getFullForecast(gridLocation: GridLocation): FullForecast {
    return api.fullForecastForPoint(gridLocation).toFullForecast()
  }
}
