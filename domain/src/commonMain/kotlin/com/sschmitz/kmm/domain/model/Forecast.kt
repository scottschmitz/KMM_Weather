package com.sschmitz.kmm.domain.model

data class Forecast (
  val name: String,
  val temperature: Int,
  val temperatureUnit: String,
  val icon: String,
  val shortForecast: String,
  val detailedForecast: String,
)
