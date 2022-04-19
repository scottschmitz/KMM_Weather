package com.sschmitz.kmm_weather.presentation.forecast

import com.sschmitz.kmm.domain.contract.WeatherContract
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.postValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ForecastViewModel(
  private val weatherContract: WeatherContract
) : ViewModel() {

  private val _mutableForecastStateLiveData = MutableLiveData<ForecastState>(ForecastState.Initial)
  val forecastStateLiveData: LiveData<ForecastState> = _mutableForecastStateLiveData

  override fun onCleared() {
    super.onCleared()

    viewModelScope.cancel()
  }

  fun refreshForecast() = viewModelScope.launch {
    val previousForecast = when (val currentState = _mutableForecastStateLiveData.value) {
      is ForecastState.Initial -> null
      is ForecastState.Loading -> return@launch
      is ForecastState.Loaded -> currentState.fullForecast
      is ForecastState.Failed -> currentState.previousForecast
    }

    _mutableForecastStateLiveData.postValue(
      ForecastState.Loading(
        previousForecast = previousForecast
      )
    )

    try {
      val updatedFullForecast = weatherContract.getFullForecast()

      _mutableForecastStateLiveData.postValue(
        ForecastState.Loaded(
          fullForecast = updatedFullForecast
        )
      )
    } catch (error: Error) {
      _mutableForecastStateLiveData.postValue(
        ForecastState.Failed(
          error = error,
          previousForecast = previousForecast
        )
      )
    }
  }
}

