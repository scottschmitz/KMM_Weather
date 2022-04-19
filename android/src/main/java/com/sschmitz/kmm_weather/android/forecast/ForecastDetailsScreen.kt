package com.sschmitz.kmm_weather.android.forecast

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ForecastDetailsScreen() {
  Surface {
    Text(
      text = "Should get more details forecast info here",
      style = MaterialTheme.typography.h1
    )
  }
}
