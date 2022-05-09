package com.sschmitz.kmm_weather.android

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
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

  private val forecastViewModel : ForecastViewModel by viewModel()

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

    setContent {
      RootView(
          forecastViewModel = forecastViewModel
      )
    }
  }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RootView(forecastViewModel: ForecastViewModel) {

  val locationPermissionState = rememberPermissionState(
    Manifest.permission.ACCESS_COARSE_LOCATION
  )
  val navController = rememberNavController()

  WeatherTheme {
    when (locationPermissionState.hasPermission) {
      true -> {

        // https://developer.android.com/training/location/request-updates
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
        val locationRequest = LocationRequest.create().apply {
          interval = 1_000 // TODO: make shared constant
          fastestInterval = 500 // TODO: make shared constant
          priority = LocationRequest.PRIORITY_LOW_POWER
        }
        val locationCallback = object : LocationCallback() {
          override fun onLocationResult(result: LocationResult) {
            forecastViewModel.updateLocation(
              latitude = result.lastLocation.latitude,
              longitude = result.lastLocation.longitude
            )
          }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        RootNavigation(
          navController = navController,
          forecastViewModel = forecastViewModel
        )
      }
      else -> {
        Column {
          val textToShow = if (locationPermissionState.shouldShowRationale) {
            // If the user has denied the permission but the rationale can be shown,
            // then gently explain why the app requires this permission
            "The location permission is critical to the app."
          } else {
            // If it's the first time the user lands on this feature, or the user
            // doesn't want to be asked again for this permission, explain that the
            // permission is required
            "Location permission required for this feature to be available. " +
              "Please grant the permission"
          }
          Text(textToShow)
          Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
            Text("Request permission")
          }
        }
      }
    }
  }
}
