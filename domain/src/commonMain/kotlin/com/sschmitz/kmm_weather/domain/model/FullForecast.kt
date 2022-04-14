package com.sschmitz.kmm_weather.domain.model

import com.sschmitz.kmm.domain.model.Forecast

data class FullForecast (
  val forecasts: List<Forecast>
) {
  companion object {
    val Empty = FullForecast(emptyList())
  }
}
