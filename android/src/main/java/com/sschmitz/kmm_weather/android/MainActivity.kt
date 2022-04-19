package com.sschmitz.kmm_weather.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.sschmitz.kmm_weather.android.navigation.RootNavigation
import com.sschmitz.kmm_weather.android.theme.WeatherTheme
import com.sschmitz.kmm_weather.presentation.forecast.ForecastState
import com.sschmitz.kmm_weather.presentation.forecast.ForecastViewModel
import dev.icerock.moko.mvvm.utils.bind
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.android.x.viewmodel.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(), DIAware {

  override val di by closestDI()

  val forecastViewModel : ForecastViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    forecastViewModel.forecastStateLiveData.bind(this) { state ->
      when (state) {
        is ForecastState.Initial -> { Timber.e("Initial") }
        is ForecastState.Loading -> { Timber.e("Loading") }
        is ForecastState.Loaded -> { Timber.e("Loaded") }
        is ForecastState.Failed -> { Timber.e("Failed") }
      }
    }
    forecastViewModel.refreshForecast()

    setContent {
      RootView(
          forecastViewModel = forecastViewModel
      )
    }
  }
}

@Composable
fun RootView(forecastViewModel: ForecastViewModel) {
  val navController = rememberNavController()

  WeatherTheme {
    RootNavigation(
      navController = navController,
      forecastViewModel = forecastViewModel
    )
  }
}
