package com.sschmitz.kmm_weather.android.location

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationTracking() {

  // Camera permission state
  val locationPermissionState = rememberPermissionState(
    android.Manifest.permission.ACCESS_COARSE_LOCATION
  )

  when (locationPermissionState.hasPermission) {
    true -> {
      Text("Camera permission Granted")
    }
    else -> {
      Column {
        val textToShow = if (locationPermissionState.shouldShowRationale) {
          // If the user has denied the permission but the rationale can be shown,
          // then gently explain why the app requires this permission
          "The camera is important for this app. Please grant the permission."
        } else {
          // If it's the first time the user lands on this feature, or the user
          // doesn't want to be asked again for this permission, explain that the
          // permission is required
          "Camera permission required for this feature to be available. " +
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
