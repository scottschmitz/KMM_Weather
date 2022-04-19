package com.sschmitz.kmm_weather.di

import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.data.di.Repositories
import com.sschmitz.kmm_weather.presentation.forecast.ForecastViewModel
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.di.instance

object ViewModelInjector {

  val kodeInContainer = DI.lazy {
    importAll(Repositories.diContainer)
  }

  private fun weatherContract() = kodeInContainer.direct.instance<WeatherContract>()

  fun forecastViewModel(): ForecastViewModel {
    return ForecastViewModel(
      weatherContract = weatherContract()
    )
  }
}
