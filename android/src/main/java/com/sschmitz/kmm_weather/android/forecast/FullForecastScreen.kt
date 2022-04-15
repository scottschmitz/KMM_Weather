package com.sschmitz.kmm_weather.android.full_forecast

import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.sschmitz.kmm_weather.android.WeatherViewModel
import com.sschmitz.kmm_weather.domain.model.FullForecast
import com.sschmitz.kmm_weather.android.forecast.ForecastList
import com.sschmitz.kmm_weather.android.navigation.NavigationPath

@Composable
fun FullForecastScreen(
  refreshForecasts: () -> Unit,
  fullForecastLiveData: LiveData<FullForecast>,
  onNavigate: (NavigationPath) -> Unit
) {
  val default = FullForecast(forecasts = emptyList())
  val fullForecast: FullForecast by fullForecastLiveData.observeAsState(default)

  refreshForecasts()

  Scaffold {
    Surface {
      ForecastList(
        forecasts = fullForecast.forecasts,
        onClick = { forecast ->
          onNavigate(NavigationPath.FORECAST_DETAILS)
        }
      )
    }
  }
}
