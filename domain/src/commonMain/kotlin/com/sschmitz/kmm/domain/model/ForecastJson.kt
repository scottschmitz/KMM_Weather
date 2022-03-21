package com.sschmitz.kmm.domain.model

import com.sschmitz.kmm.domain.model.Forecast
import kotlinx.serialization.Serializable

@Serializable
internal data class ForecastJson (
  val name: String,
  val temperature: Int,
  val temperatureUnit: String,
  val icon: String,
  val shortForecast: String,
  val detailedForecast: String,
) {
  fun toForecast(): Forecast {
    return Forecast(
      name = name,
      temperature = temperature,
      temperatureUnit = temperatureUnit,
      icon = icon,
      shortForecast = shortForecast,
      detailedForecast = detailedForecast
    )
  }
}
