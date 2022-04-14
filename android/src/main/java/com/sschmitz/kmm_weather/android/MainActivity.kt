package com.sschmitz.kmm_weather.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.android.navigation.RootNavigation
import com.sschmitz.kmm_weather.android.theme.WeatherTheme
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {


  private val x = ViewModelProviders.of(this).get(GitHubViewModel::class.java)

  private val contract: WeatherContract by instance()

  override val di by lazy { (applicationContext as App).di }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      RootView(
          contract = contract
      )
    }
  }
}

@Composable
fun RootView(contract: WeatherContract) {
  val navController = rememberNavController()

  WeatherTheme {
    RootNavigation(
      navController = navController,
      contract = contract
    )
  }
}
