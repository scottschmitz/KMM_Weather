package com.sschmitz.kmm.domain.model

import com.sschmitz.kmm.domain.model.FullForecast
import kotlinx.serialization.Serializable

@Serializable
internal data class FullForecastJson (
  val properties: FullForecastProperties
) {
  fun toFullForecast(): FullForecast {
    return FullForecast(
      forecasts = properties.periods.map { it.toForecast() }
    )
  }
}

@Serializable
internal data class FullForecastProperties(
  val periods: List<ForecastJson>
)
