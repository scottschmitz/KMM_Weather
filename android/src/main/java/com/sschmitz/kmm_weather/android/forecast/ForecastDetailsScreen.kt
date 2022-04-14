package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.sschmitz.kmm.domain.model.Forecast

@Composable
fun ForecastDetailsScreen(
  forecast: Forecast
) {
  Surface {
    ForecastOverview(
      forecast = forecast,
      onClick = {}
    )
  }
}
