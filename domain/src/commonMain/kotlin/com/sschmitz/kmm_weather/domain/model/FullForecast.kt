package com.sschmitz.kmm_weather.domain.model

data class FullForecast (
  val forecasts: List<Forecast>
) {
  companion object {
    val Empty = FullForecast(emptyList())
  }
}
