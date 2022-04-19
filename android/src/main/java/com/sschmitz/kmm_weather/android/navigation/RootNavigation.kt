@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.sschmitz.kmm_weather.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sschmitz.kmm.data.repository.WeatherRepository
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.android.WeatherViewModel
import com.sschmitz.kmm_weather.android.forecast.ForecastDetailsScreen
import com.sschmitz.kmm_weather.android.full_forecast.FullForecastScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RootNavigation(
  navController: NavHostController,
  weatherViewModel: WeatherViewModel
) {
  NavHost(navController = navController, startDestination = NavigationPath.FULL_FORECAST.route) {
    composable(NavigationPath.FULL_FORECAST.route) {
      FullForecastScreen(
        refreshForecasts = weatherViewModel::getFullForecast,
        fullForecastLiveData = weatherViewModel.fullForecastLiveData,
        onNavigate = { path -> navController.navigate(path.route) },
      )
    }
    composable(NavigationPath.FORECAST_DETAILS.route) {
      ForecastDetailsScreen()
    }
  }
}
