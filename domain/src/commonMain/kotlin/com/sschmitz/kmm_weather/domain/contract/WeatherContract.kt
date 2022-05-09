package com.sschmitz.kmm_weather.domain.contract

import com.sschmitz.kmm_weather.domain.model.FullForecast

interface WeatherContract {
  @Throws(Exception::class)
  suspend fun getGridPositions(latitude: Double, longitude: Double): GridLocation

  @Throws(Exception::class)
  suspend fun getFullForecast(gridLocation: GridLocation): FullForecast
}

data class GridLocation(
  val x: Int,
  val y: Int
)
