package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sschmitz.kmm.domain.model.Forecast

@Composable
fun ForecastOverview(
  forecast: Forecast,
  onClick: (Forecast) -> Unit
) {

  Box(
    modifier = Modifier.clickable { onClick(forecast) }
  ) {
    Text(text = forecast.detailedForecast)
  }
}
