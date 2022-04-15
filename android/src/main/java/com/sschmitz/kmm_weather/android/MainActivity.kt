package com.sschmitz.kmm_weather.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sschmitz.kmm_weather.android.navigation.RootNavigation
import com.sschmitz.kmm_weather.android.theme.WeatherTheme
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.android.x.viewmodel.viewModel

class MainActivity : AppCompatActivity(), DIAware {
  override val di by closestDI()

//  val weatherViewModel by lazy { ViewModelFactory(di).create(WeatherViewModel::class.java) }

  val weatherViewModel: WeatherViewModel by viewModel()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      RootView(
          weatherViewModel = weatherViewModel
      )
    }
  }
}

@Composable
fun RootView(weatherViewModel: WeatherViewModel) {
  val navController = rememberNavController()

  WeatherTheme {
    RootNavigation(
      navController = navController,
      weatherViewModel = weatherViewModel
    )
  }
}
