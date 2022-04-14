package com.sschmitz.kmm_weather.android

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sschmitz.kmm.domain.contract.WeatherContract
import com.sschmitz.kmm_weather.domain.model.FullForecast
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.instance

object ViewModelInjection {
  val diContainer = DI.Module("ViewModels") {
    bind<WeatherViewModel>() with instance(WeatherViewModel())
  }
}

@ExperimentalCoroutinesApi
class WeatherViewModel(val contract: WeatherContract) : ViewModel(), DIAware {

  private val mutableFullForecastLiveData = MutableLiveData(FullForecast.Empty)
  val fullForecastLiveData: LiveData<FullForecast> = mutableFullForecastLiveData

  fun getFullForecast(username: String) {
    viewModelScope.launch() {
      try {
        val fullForecast = contract.getFullForecast()
        mutableFullForecastLiveData.value = fullForecast
      } catch (exception: Exception) {

      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    viewModelScope.cancel()
  }
}
