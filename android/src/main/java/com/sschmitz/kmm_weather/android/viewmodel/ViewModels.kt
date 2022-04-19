package com.sschmitz.kmm_weather.android.viewmodel

import com.sschmitz.kmm_weather.presentation.forecast.ForecastViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object ViewModels {
  val diContainer = DI.Module("ViewModels") {
    bindProvider {
      ForecastViewModel(
        weatherContract = instance()
      )
    }
  }
}
