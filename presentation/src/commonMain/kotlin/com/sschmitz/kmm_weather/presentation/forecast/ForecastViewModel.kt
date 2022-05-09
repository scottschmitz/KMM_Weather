package com.sschmitz.kmm_weather.presentation.forecast

import com.sschmitz.kmm_weather.domain.contract.GridLocation
import com.sschmitz.kmm_weather.domain.contract.WeatherContract
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

  private var latestGridLocation: GridLocation? = null

  override fun onCleared() {
    super.onCleared()

    viewModelScope.cancel()
  }

  fun updateLocation(latitude: Double, longitude: Double) = viewModelScope.launch {
    try {
      latestGridLocation = weatherContract.getGridPositions(latitude, longitude)
      refreshForecast()
    } catch (e: Exception) {
      // TODO: Some kinda error
    }
  }

  fun refreshForecast() = viewModelScope.launch {
    val previousForecast = when (val currentState = _mutableForecastStateLiveData.value) {
      is ForecastState.Initial -> null
      is ForecastState.Loading -> currentState.previousForecast
      is ForecastState.Loaded -> currentState.fullForecast
      is ForecastState.Failed -> currentState.previousForecast
    }

    _mutableForecastStateLiveData.postValue(
      ForecastState.Loading(
        previousForecast = previousForecast
      )
    )

    try {
      val updatedFullForecast = weatherContract.getFullForecast(latestGridLocation!!)

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
    } catch (exception: Exception) {
      _mutableForecastStateLiveData.postValue(
        ForecastState.Failed(
          error = Error(),
          previousForecast = previousForecast
        )
      )
    }
  }
}

