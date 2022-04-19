package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sschmitz.kmm_weather.android.R
import com.sschmitz.kmm_weather.android.theme.WeatherTheme
import com.sschmitz.kmm_weather.domain.model.Forecast

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
        placeholder = painterResource(id = R.drawable.ic_100tb),
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .width(50.dp)
          .height(50.dp),
        contentDescription = null
      )

      Text(
        text = "${forecast.temperature}${forecast.temperatureUnit}",
        style = MaterialTheme.typography.subtitle1
      )
    }
  }
}

@Preview
@Composable
fun PreviewForecastOverview() {
  val forecast = Forecast(
    name = "This Afternoon",
    temperature = 46,
    temperatureUnit = "F",
    icon = "https://api.weather.gov/icons/land/day/rain,30?size=medium",
    shortForecast = "Chance Light Rain",
    detailedForecast = "A chance of rain. Cloudy. High near 46, with temperatures falling to around 44 in the afternoon. West southwest wind around 20 mph, with gusts as high as 33 mph. Chance of precipitation is 30%.",
  )

  WeatherTheme {
    ForecastOverview(
      forecast = forecast,
      onClick = {  }
    )
  }
}
