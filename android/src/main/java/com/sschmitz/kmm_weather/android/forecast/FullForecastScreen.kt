package com.sschmitz.kmm_weather.android.full_forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.sschmitz.kmm_weather.domain.model.FullForecast
import com.sschmitz.kmm_weather.android.forecast.ForecastList
import com.sschmitz.kmm_weather.android.navigation.NavigationPath
import com.sschmitz.kmm_weather.presentation.forecast.ForecastState

@Composable
fun FullForecastScreen(
  refreshForecasts: () -> Unit,
  forecastStateLiveData: LiveData<ForecastState>,
  onNavigate: (NavigationPath) -> Unit
) {
  val default = ForecastState.Loading(null)
  val state: ForecastState by forecastStateLiveData.observeAsState(default)

  refreshForecasts()

  Scaffold {
    Surface {
      when (val tempState = state) {
        is ForecastState.Loading -> LoadingFullForecast(
          previousFullForecast = tempState.previousForecast,
          onNavigate = onNavigate
        )
        is ForecastState.Loaded -> LoadedFullForecast(
          fullForecast = tempState.fullForecast,
          onNavigate = onNavigate
        )
        is ForecastState.Failed -> FailedFullForecast(
          previousFullForecast = tempState.previousForecast,
          onNavigate = onNavigate
        )
      }
    }
  }
}

@Composable
fun LoadingFullForecast(
  previousFullForecast: FullForecast?,
  onNavigate: (NavigationPath) -> Unit
) {
  ForecastList(
    forecasts = previousFullForecast?.forecasts ?: emptyList(),
    onClick = { forecast ->
      onNavigate(NavigationPath.FORECAST_DETAILS)
    }
  )
}

@Composable
fun LoadedFullForecast(
  fullForecast: FullForecast,
  onNavigate: (NavigationPath) -> Unit
) {
  CircularProgressIndicator()

  ForecastList(
    forecasts = fullForecast.forecasts,
    onClick = { forecast ->
      onNavigate(NavigationPath.FORECAST_DETAILS)
    }
  )
}

@Composable
fun FailedFullForecast(
  previousFullForecast: FullForecast?,
  onNavigate: (NavigationPath) -> Unit
) {
  Text(text = "Failed to update forecast.")

  ForecastList(
    forecasts = previousFullForecast?.forecasts ?: emptyList(),
    onClick = { forecast ->
      onNavigate(NavigationPath.FORECAST_DETAILS)
    }
  )
}



//@Preview
//@Composable
//fun PreviewForecastScreen() {
//  val forecasts = listOf(
//    Forecast(
//      name = "Tonight",
//      temperature = 33,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/snow,50?size=medium",
//      shortForecast = "Chance Rain And Snow Showers",
//      detailedForecast = "A chance of rain and snow showers. Cloudy, with a low around 33. West northwest wind around 10 mph, with gusts as high as 22 mph. Chance of precipitation is 50%. Little or no snow accumulation expected."
//    ),
//    Forecast(
//      name = "Tuesday",
//      temperature = 44,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/day/snow,20/bkn?size=medium",
//      shortForecast = "Slight Chance Snow Showers then Mostly Cloudy",
//      detailedForecast = "A slight chance of snow showers before 8am. Mostly cloudy, with a high near 44. Northwest wind 10 to 18 mph, with gusts as high as 30 mph. Chance of precipitation is 20%."
//    ),
//    Forecast(
//      name = "Tuesday Night",
//      temperature = 29,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/sct?size=medium",
//      shortForecast = "Partly Cloudy",
//      detailedForecast = "Partly cloudy, with a low around 29. Southwest wind 1 to 15 mph, with gusts as high as 28 mph."
//    ),
//    Forecast(
//      name = "Wednesday",
//      temperature = 52,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/day/bkn/rain_showers,30?size=medium",
//      shortForecast = "Mostly Cloudy then Chance Rain Showers",
//      detailedForecast = "A chance of rain showers after 2pm. Mostly cloudy, with a high near 52. Southeast wind 3 to 16 mph, with gusts as high as 30 mph. Chance of precipitation is 30%."
//    ),
//    Forecast(
//      name = "Wednesday Night",
//      temperature = 45,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/rain_showers,90?size=medium",
//      shortForecast = "Rain Showers",
//      detailedForecast = "Rain showers. Cloudy, with a low around 45. South wind 12 to 18 mph, with gusts as high as 30 mph. Chance of precipitation is 90%."
//    ),
//    Forecast(
//      name = "Thursday",
//      temperature = 62,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/day/rain_showers,70/sct?size=medium",
//      shortForecast = "Rain Showers Likely then Mostly Sunny",
//      detailedForecast = "Rain showers likely before 8am. Mostly sunny, with a high near 62. Chance of precipitation is 70%."
//    ),
//    Forecast(
//      name = "Thursday Night",
//      temperature = 39,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/few?size=medium",
//      shortForecast = "Mostly Clear",
//      detailedForecast = "Mostly clear, with a low around 39."
//    ),
//    Forecast(
//      name = "Friday",
//      temperature = 62,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/day/rain_showers?size=medium",
//      shortForecast = "Chance Rain Showers",
//      detailedForecast = "A chance of rain showers after 8am. Partly sunny, with a high near 62."
//    ),
//    Forecast(
//      name = "Friday Night",
//      temperature = 49,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/tsra?size=medium",
//      shortForecast = "Chance Showers And Thunderstorms",
//      detailedForecast = "A chance of rain showers before 8pm, then a chance of showers and thunderstorms. Mostly cloudy, with a low around 49."
//    ),
//    Forecast(
//      name = "Saturday",
//      temperature = 76,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/day/rain_showers?size=medium",
//      shortForecast = "Chance Rain Showers",
//      detailedForecast = "A chance of rain showers before 2pm. Mostly cloudy, with a high near 76."
//    ),
//    Forecast(
//      name = "Saturday Night",
//      temperature = 60,
//      temperatureUnit = "F",
//      icon = "https://api.weather.gov/icons/land/night/rain_showers?size=medium",
//      shortForecast = "Chance Rain Showers",
//      detailedForecast = "A chance of rain showers after 8pm. Mostly cloudy, with a low around 60."
//    )
//  )
//
//  val fullForecast = FullForecast(
//    forecasts = forecasts
//  )
//
//  WeatherTheme {
//    FullForecastScreen(
//      refreshForecasts = { },
//      forecastStateLiveData = MutableLiveData(fullForecast),
//      onNavigate = { }
//    )
//  }
//}

