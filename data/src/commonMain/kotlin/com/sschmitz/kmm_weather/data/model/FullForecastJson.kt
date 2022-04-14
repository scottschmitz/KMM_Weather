package com.sschmitz.kmm.data.model

import com.sschmitz.kmm_weather.domain.model.FullForecast
import kotlinx.serialization.Serializable

@Serializable
data class FullForecastJson (
  val properties: FullForecastProperties
) {
  fun toFullForecast(): FullForecast {
    return FullForecast(
      forecasts = properties.periods.map { it.toForecast() }
    )
  }
}

@Serializable
data class FullForecastProperties(
  val periods: List<ForecastJson>
)
