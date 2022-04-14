package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sschmitz.kmm.domain.model.Forecast

@Composable
fun ForecastList (
  forecasts: List<Forecast>,
  onClick: (Forecast) -> Unit
) {
  LazyColumn {
    items(forecasts) { forecast ->
      ForecastOverview(
        forecast = forecast,
        onClick = onClick
      )
    }
  }
}
