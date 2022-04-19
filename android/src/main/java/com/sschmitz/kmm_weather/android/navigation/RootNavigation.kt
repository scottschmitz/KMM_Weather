@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.sschmitz.kmm_weather.android.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sschmitz.kmm_weather.android.forecast.ForecastDetailsScreen
import com.sschmitz.kmm_weather.android.full_forecast.FullForecastScreen
import com.sschmitz.kmm_weather.presentation.forecast.ForecastViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RootNavigation(
  navController: NavHostController,
  forecastViewModel: ForecastViewModel
) {
  NavHost(navController = navController, startDestination = NavigationPath.FULL_FORECAST.route) {
    composable(NavigationPath.FULL_FORECAST.route) {
      FullForecastScreen(
        refreshForecasts = forecastViewModel::refreshForecast,
        forecastStateLiveData = forecastViewModel.forecastStateLiveData.ld(),
        onNavigate = { path -> navController.navigate(path.route) },
      )
    }
    composable(NavigationPath.FORECAST_DETAILS.route) {
      ForecastDetailsScreen()
    }
  }
}
