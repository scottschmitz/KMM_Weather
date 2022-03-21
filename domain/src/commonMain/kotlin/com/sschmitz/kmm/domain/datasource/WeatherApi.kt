package com.sschmitz.kmm.domain.datasource

import com.sschmitz.kmm.domain.model.FullForecastJson
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

internal class WeatherApi {

  private val client = HttpClient {
    install(JsonFeature) {
      serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
        isLenient = true
        ignoreUnknownKeys = true
      })
    }
  }


  private val address = "https://api.weather.gov/gridpoints/GRR/46,46/forecast"

  suspend fun fullForecastForPoint(): FullForecastJson {
    return client.get(address)
  }
}
