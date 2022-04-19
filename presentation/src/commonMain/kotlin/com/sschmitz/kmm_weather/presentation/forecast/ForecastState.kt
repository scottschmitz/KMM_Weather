package com.sschmitz.kmm_weather.presentation.forecast

import com.sschmitz.kmm_weather.domain.model.FullForecast

sealed class ForecastState {
  object Initial : ForecastState()
  data class Loading(val previousForecast: FullForecast?) : ForecastState()
  data class Loaded(val fullForecast: FullForecast) : ForecastState()
  data class Failed(val error: Error, val previousForecast: FullForecast?) : ForecastState()
}
