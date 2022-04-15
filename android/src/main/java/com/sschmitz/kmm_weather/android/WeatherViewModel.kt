package com.sschmitz.kmm_weather.android

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.domain.model.FullForecast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.instanceOrNull
import timber.log.Timber

@ExperimentalCoroutinesApi
class WeatherViewModel(val contract: WeatherContract) : ViewModel() {

  private val mutableFullForecastLiveData = MutableLiveData(FullForecast.Empty)
  val fullForecastLiveData: LiveData<FullForecast> = mutableFullForecastLiveData

  fun getFullForecast() {
    viewModelScope.launch() {
      try {
        val fullForecast = contract.getFullForecast()
        mutableFullForecastLiveData.value = fullForecast
      } catch (exception: Exception) {
        Timber.e(exception, "Failed to get it")
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    viewModelScope.cancel()
  }
}


object ViewModels {
  val diContainer = DI.Module("ViewModels") {
    bindProvider {
      WeatherViewModel(
        contract = instance()
      )
    }
  }
}
