package com.sschmitz.kmm.data.model

import com.sschmitz.kmm_weather.domain.model.Forecast
import kotlinx.serialization.Serializable

@Serializable
data class ForecastJson (
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
