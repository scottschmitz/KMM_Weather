package com.sschmitz.kmm_weather.data.model

import com.sschmitz.kmm_weather.domain.contract.GridLocation
import kotlinx.serialization.Serializable

@Serializable
data class PointsJson(
  val properties: PointPropertiesJson
) {
  fun toGridLocation(): GridLocation {
    return GridLocation(
      x = properties.gridX,
      y = properties.gridY
    )
  }
}

@Serializable
data class PointPropertiesJson(
  val gridX: Int,
  val gridY: Int
)
