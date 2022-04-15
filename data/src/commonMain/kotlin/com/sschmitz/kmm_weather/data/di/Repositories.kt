package com.sschmitz.kmm_weather.data.di

import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm.data.repository.WeatherRepository
import io.ktor.util.reflect.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.singleton

object Repositories {
  val diContainer = DI.Module("Repositories") {
    bind<WeatherContract>() with singleton { WeatherRepository() }
  }
}

object RepositoriesInjector {
  val kodeInContainer = DI.lazy {
    importAll(Repositories.diContainer)
  }

  fun weatherContract() = kodeInContainer.direct.instance<WeatherContract>()
}
