package com.sschmitz.kmm.domain.contract

import com.sschmitz.kmm.domain.model.FullForecast

interface WeatherContract {
  suspend fun getFullForecast(): FullForecast
}
