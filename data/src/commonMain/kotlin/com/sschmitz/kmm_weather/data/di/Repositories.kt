package com.sschmitz.kmm_weather.data.di

import com.sschmitz.kmm_weather.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.data.repository.WeatherRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

object Repositories {
  val diContainer = DI.Module("Repositories") {
    bind<WeatherContract>() with singleton { WeatherRepository() }
  }
}

//object RepositoriesInjector {
//  val kodeInContainer = DI.lazy {
//    importAll(Repositories.diContainer)
//  }
//
//  fun weatherContract() = kodeInContainer.direct.instance<WeatherContract>()
//}
