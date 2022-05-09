package com.sschmitz.kmm_weather.data.datasource

import com.sschmitz.kmm_weather.data.model.FullForecastJson
import com.sschmitz.kmm_weather.data.model.PointsJson
import com.sschmitz.kmm_weather.domain.contract.GridLocation
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

  suspend fun getGridLocation(latitude: Double, longitude: Double): PointsJson {
    val address = "https://api.weather.gov/points/$latitude,$longitude"
    return client.get(address)
  }

  suspend fun fullForecastForPoint(gridLocation: GridLocation): FullForecastJson {
    val address = "https://api.weather.gov/gridpoints/GRR/${gridLocation.x},${gridLocation.y}/forecast"
    return client.get(address)
  }
}
