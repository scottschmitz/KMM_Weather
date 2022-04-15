package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sschmitz.kmm.domain.model.Forecast

@Composable
fun ForecastOverview(
  forecast: Forecast,
  onClick: (Forecast) -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick(forecast) }
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = forecast.name,
          style = MaterialTheme.typography.subtitle1,
        )
        Text(
          text = forecast.shortForecast,
          style = MaterialTheme.typography.subtitle2,
        )
      }

      AsyncImage(
        model = forecast.icon,
        contentDescription = null
      )

      Text(
        text = "${forecast.temperature}${forecast.temperatureUnit}",
        style = MaterialTheme.typography.subtitle1
      )
    }
  }
}
