package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.sschmitz.kmm_weather.android.full_forecast.FullForecastScreen
import com.sschmitz.kmm_weather.android.theme.WeatherTheme
import com.sschmitz.kmm_weather.domain.model.Forecast
import com.sschmitz.kmm_weather.domain.model.FullForecast

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

@Preview
@Composable
fun PreviewForecastList() {
  val forecast = Forecast(
    name = "This Afternoon",
    temperature = 46,
    temperatureUnit = "F",
    icon = "https://api.weather.gov/icons/land/day/rain,30?size=medium",
    shortForecast = "Chance Light Rain",
    detailedForecast = "A chance of rain. Cloudy. High near 46, with temperatures falling to around 44 in the afternoon. West southwest wind around 20 mph, with gusts as high as 33 mph. Chance of precipitation is 30%.",
  )
  val forecasts = listOf(forecast)

  WeatherTheme {
    ForecastList(
      forecasts = forecasts,
      onClick = { _ ->  }
    )
  }
}
